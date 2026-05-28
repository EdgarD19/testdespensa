package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.*;
import com.proyecto2.DespensaProyect.model.request.LineaVentaRequest;
import com.proyecto2.DespensaProyect.model.request.VentaFacturaRequest;
import com.proyecto2.DespensaProyect.model.response.VentaFacturaResponse;
import com.proyecto2.DespensaProyect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VentaFacturaService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    @Autowired
    private TransferenciaPagoRepository transferenciaPagoRepository;

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private MovimientoCajaRepository movimientoCajaRepository;

    @Autowired
    private TipoMovimientoCajaRepository tipoMovimientoCajaRepository;

    public VentaFacturaResponse registrar(VentaFacturaRequest request) {
        // Validar carrito no vacío
        if (request.getLineas() == null || request.getLineas().isEmpty()) {
            throw new IllegalArgumentException("La venta debe incluir al menos una línea");
        }

        // Validar total contra líneas
        BigDecimal totalCalculado = BigDecimal.ZERO;
        for (LineaVentaRequest linea : request.getLineas()) {
            BigDecimal subtotal = linea.getCantidad().multiply(linea.getPrecioUnitario());
            totalCalculado = totalCalculado.add(subtotal);
        }
        if (request.getTotal() != null && totalCalculado.compareTo(request.getTotal()) != 0) {
            throw new IllegalArgumentException("El total no coincide con la suma de las líneas");
        }

        // Validar pago suficiente
        BigDecimal montoPagado = request.getMontoPagado() != null ? request.getMontoPagado() : totalCalculado;
        if (montoPagado.compareTo(totalCalculado) < 0) {
            throw new IllegalArgumentException("El monto pagado no cubre el total de la venta");
        }

        // Buscar cliente si se especificó
        Cliente cliente = null;
        if (request.getIdCliente() != null) {
            cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getIdCliente()));
        }

        // Descontar stock de cada producto
        List<Producto> productosModificados = new ArrayList<>();
        for (LineaVentaRequest linea : request.getLineas()) {
            Producto p = productoRepository.findById(linea.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + linea.getIdProducto()));
            BigDecimal cant = linea.getCantidad();
            if (cant.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero para " + p.getNombre());
            }
            if (!p.tieneStock(cant)) {
                throw new IllegalStateException(
                        "Stock insuficiente para " + p.getNombre() + ". Disponible: " + p.getStockActual());
            }
            p.decrementarStock(cant);
            productosModificados.add(p);
        }
        productoRepository.saveAll(productosModificados);

        // Crear factura
        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(parseFechaFactura(request.getFechaFactura()));
        factura.setNroFactura(generarNroFacturaUnico());
        factura.setTipoFactura(request.getTipoFactura() != null ? request.getTipoFactura() : "CONTADO");
        factura.setEstado(request.getEstado() != null ? request.getEstado() : "PENDIENTE");
        factura.setMontoPagado(montoPagado);
        factura.setCambio(request.getCambio());

        // Buscar forma de pago
        if (request.getFormaPago() != null && !request.getFormaPago().isBlank()) {
            FormaPago fp = formaPagoRepository.findByDescripcion(request.getFormaPago())
                    .orElseGet(() -> formaPagoRepository.findByDescripcionContainingIgnoreCase(request.getFormaPago())
                            .stream().findFirst().orElse(null));
            factura.setFormaPago(fp);
        }

        // Crear detalles de factura
        List<DetalleFactura> detallesFactura = new ArrayList<>();
        for (LineaVentaRequest linea : request.getLineas()) {
            Producto p = productoRepository.findById(linea.getIdProducto()).orElseThrow();
            DetalleFactura df = DetalleFactura.builder()
                    .factura(factura)
                    .producto(p)
                    .cantidad(linea.getCantidad())
                    .precioUnitario(linea.getPrecioUnitario())
                    .build();
            detallesFactura.add(df);
        }
        factura.setDetalles(detallesFactura);
        factura.calcularTotal();

        Factura savedFactura = facturaRepository.save(factura);

        // Crear movimiento de ingreso en caja
        crearMovimientoCajaIngreso(savedFactura);

        // Si la forma de pago es transferencia, guardar detalles bancarios
        if (request.getFormaPago() != null && "TRANSFERENCIA".equalsIgnoreCase(request.getFormaPago().trim())) {
            TransferenciaPago tp = TransferenciaPago.builder()
                    .banco(request.getBanco())
                    .numeroComprobante(request.getComprobante())
                    .titular(request.getTitular())
                    .fechaTransferencia(LocalDateTime.now())
                    .monto(savedFactura.getTotal())
                    .factura(savedFactura)
                    .build();
            transferenciaPagoRepository.save(tp);
        }

        return VentaFacturaResponse.builder()
                .idFactura(savedFactura.getIdFactura())
                .numeroFactura(savedFactura.getNroFactura())
                .build();
    }

    private void crearMovimientoCajaIngreso(Factura factura) {
        Caja cajaAbierta = cajaRepository.findCajaAbierta().orElse(null);
        if (cajaAbierta == null) return;

        TipoMovimientoCaja tipoIngreso = tipoMovimientoCajaRepository.findByNombre("INGRESO").orElse(null);
        if (tipoIngreso == null) return;

        MovimientoCaja mov = MovimientoCaja.builder()
                .caja(cajaAbierta)
                .tipoMovimientoCaja(tipoIngreso)
                .formaPago(factura.getFormaPago())
                .monto(factura.getTotal())
                .motivo("Venta factura N° " + factura.getNroFactura())
                .fecha(LocalDateTime.now())
                .build();
        movimientoCajaRepository.save(mov);
    }

    private static LocalDateTime parseFechaFactura(String fechaFactura) {
        if (fechaFactura == null || fechaFactura.isBlank()) {
            return LocalDateTime.now();
        }
        try {
            return LocalDate.parse(fechaFactura.trim()).atStartOfDay();
        } catch (DateTimeParseException e) {
            return LocalDateTime.now();
        }
    }

    private String generarNroFacturaUnico() {
        String fechaPart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = facturaRepository.count() + 1;
        String candidato = "FACT-" + fechaPart + "-" + String.format("%05d", count);
        if (!facturaRepository.existsByNroFactura(candidato)) {
            return candidato;
        }
        // Si hay colisión, incrementar hasta encontrar uno libre
        for (long i = count + 1; i < count + 100; i++) {
            candidato = "FACT-" + fechaPart + "-" + String.format("%05d", i);
            if (!facturaRepository.existsByNroFactura(candidato)) {
                return candidato;
            }
        }
        return "FACT-" + fechaPart + "-" + System.currentTimeMillis();
    }
}
