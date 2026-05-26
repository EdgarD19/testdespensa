package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.*;
import com.proyecto2.DespensaProyect.model.request.LineaVentaRequest;
import com.proyecto2.DespensaProyect.model.request.VentaFacturaRequest;
import com.proyecto2.DespensaProyect.model.response.VentaFacturaResponse;
import com.proyecto2.DespensaProyect.repository.ClienteRepository;
import com.proyecto2.DespensaProyect.repository.FacturaRepository;
import com.proyecto2.DespensaProyect.repository.FormaPagoRepository;
import com.proyecto2.DespensaProyect.repository.ProductoRepository;
import com.proyecto2.DespensaProyect.repository.TransferenciaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public VentaFacturaResponse registrar(VentaFacturaRequest request) {
        if (request.getLineas() == null || request.getLineas().isEmpty()) {
            throw new IllegalArgumentException("La venta debe incluir al menos una línea");
        }

        Cliente cliente = null;
        if (request.getIdCliente() != null) {
            cliente = clienteRepository.findById(request.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getIdCliente()));
        }

        List<Producto> productosModificados = new ArrayList<>();
        for (LineaVentaRequest linea : request.getLineas()) {
            Producto p = productoRepository.findById(linea.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + linea.getIdProducto()));
            BigDecimal cant = linea.getCantidad();
            if (!p.tieneStock(cant)) {
                throw new IllegalStateException(
                        "Stock insuficiente para " + p.getNombre() + ". Disponible: " + p.getStockActual());
            }
            p.decrementarStock(cant);
            productosModificados.add(p);
        }
        productoRepository.saveAll(productosModificados);

        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(parseFechaFactura(request.getFechaFactura()));
        factura.setNroFactura(generarNroFacturaUnico());

        // Buscar forma de pago
        if (request.getFormaPago() != null && !request.getFormaPago().isBlank()) {
            FormaPago fp = formaPagoRepository.findByDescripcion(request.getFormaPago())
                    .orElseGet(() -> formaPagoRepository.findByDescripcionContainingIgnoreCase(request.getFormaPago())
                            .stream().findFirst().orElse(null));
            factura.setFormaPago(fp);
        }

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

        // Si la forma de pago es transferencia, guardamos los detalles bancarios
        if (request.getFormaPago() != null && "Transferencia".equalsIgnoreCase(request.getFormaPago().trim())) {
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
        for (int i = 0; i < 10; i++) {
            String candidato = "VTA-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
            if (!facturaRepository.existsByNroFactura(candidato)) {
                return candidato;
            }
        }
        return "VTA-" + System.currentTimeMillis();
    }
}
