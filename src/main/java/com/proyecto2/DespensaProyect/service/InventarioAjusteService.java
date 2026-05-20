package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.InventarioAjuste;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.model.request.InventarioAjusteRequest;
import com.proyecto2.DespensaProyect.model.response.InventarioAjusteResponse;
import com.proyecto2.DespensaProyect.repository.InventarioAjusteRepository;
import com.proyecto2.DespensaProyect.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InventarioAjusteService {

    @Autowired
    private InventarioAjusteRepository inventarioAjusteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> listar(int page, int pageSize) {
        Page<InventarioAjuste> p = inventarioAjusteRepository.findAllByOrderByIdDesc(
                PageRequest.of(Math.max(page, 0), Math.min(Math.max(pageSize, 1), 200)));
        List<InventarioAjusteResponse> content = p.getContent().stream().map(this::toResponse).toList();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("content", content);
        body.put("totalElements", p.getTotalElements());
        body.put("totalPages", p.getTotalPages());
        body.put("page", p.getNumber());
        body.put("size", p.getSize());
        return body;
    }

    public InventarioAjusteResponse crear(InventarioAjusteRequest request) {
        if (request.getIdProducto() == null) {
            throw new IllegalArgumentException("idProducto es obligatorio");
        }
        if (request.getNuevoStock() == null) {
            throw new IllegalArgumentException("nuevoStock es obligatorio");
        }

        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + request.getIdProducto()));

        BigDecimal anterior = producto.getStockActual() != null ? producto.getStockActual() : BigDecimal.ZERO;
        BigDecimal nuevo = request.getNuevoStock();

        if (nuevo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        producto.setStockActual(nuevo);
        productoRepository.save(producto);

        BigDecimal diff = nuevo.subtract(anterior);
        LocalDate fecha = parseFecha(request.getFechaAjuste());

        InventarioAjuste ajuste = InventarioAjuste.builder()
                .producto(producto)
                .tipoAjuste(request.getTipoAjuste() != null ? request.getTipoAjuste() : "")
                .fechaAjuste(fecha)
                .stockAnterior(anterior)
                .nuevoStock(nuevo)
                .diferencia(diff)
                .justificacion(request.getJustificacion() != null ? request.getJustificacion() : "")
                .detalleOtro(request.getDetalleOtro() != null ? request.getDetalleOtro() : "")
                .autorizadoPor(request.getAutorizadoPor() != null ? request.getAutorizadoPor().trim() : "")
                .estado("AUTORIZADO")
                .build();

        inventarioAjusteRepository.save(ajuste);
        return toResponse(ajuste);
    }

    public InventarioAjusteResponse autorizar(Long id) {
        InventarioAjuste ajuste = inventarioAjusteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ajuste no encontrado con id: " + id));

        if ("AUTORIZADO".equalsIgnoreCase(ajuste.getEstado())) {
            return toResponse(ajuste);
        }

        if (!"PENDIENTE_DE_AUTORIZACION".equalsIgnoreCase(ajuste.getEstado())) {
            throw new IllegalStateException("No se puede autorizar: el ajuste no está pendiente de autorización");
        }

        Producto producto = ajuste.getProducto();
        producto.setStockActual(ajuste.getNuevoStock());
        productoRepository.save(producto);

        ajuste.setEstado("AUTORIZADO");
        inventarioAjusteRepository.save(ajuste);
        return toResponse(ajuste);
    }

    private LocalDate parseFecha(String raw) {
        if (raw == null || raw.isBlank()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(raw.trim());
        } catch (DateTimeParseException e) {
            return LocalDate.now();
        }
    }

    private InventarioAjusteResponse toResponse(InventarioAjuste a) {
        Producto p = a.getProducto();
        return InventarioAjusteResponse.builder()
                .id(a.getId())
                .idProducto(p != null ? p.getIdProducto() : null)
                .nombreProducto(p != null ? p.getNombre() : "")
                .tipoAjuste(a.getTipoAjuste())
                .fechaAjuste(a.getFechaAjuste() != null ? a.getFechaAjuste().toString() : "")
                .stockAnterior(a.getStockAnterior())
                .nuevoStock(a.getNuevoStock())
                .diferencia(a.getDiferencia())
                .justificacion(a.getJustificacion())
                .detalleOtro(a.getDetalleOtro())
                .autorizadoPor(a.getAutorizadoPor())
                .estado(a.getEstado())
                .build();
    }
}
