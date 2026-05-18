package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.RoundingMode;

@Entity
@Table(name = "historial_precio_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"producto", "proveedor"})
@EqualsAndHashCode(exclude = {"producto", "proveedor"})
public class
HistorialPrecioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_precio_producto")
    private Long idHistorialPrecioProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    // ==================== PRECIOS DE VENTA ====================

    @Column(name = "precio_anterior", precision = 10, scale = 2)
    private BigDecimal precioAnterior;

    @Column(name = "precio_nuevo", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioNuevo;

    // ==================== PRECIOS DE COSTO ====================

    @Column(name = "precio_costo_anterior", precision = 10, scale = 2)
    private BigDecimal precioCostoAnterior;

    @Column(name = "precio_costo_nuevo", precision = 10, scale = 2)
    private BigDecimal precioCostoNuevo;

    // ==================== MÁRGENES EN MONTO ====================

    @Column(name = "margen_anterior", precision = 10, scale = 2)
    private BigDecimal margenAnterior;

    @Column(name = "margen_nuevo", precision = 10, scale = 2)
    private BigDecimal margenNuevo;

    // ==================== MÁRGENES EN PORCENTAJE ====================

    @Column(name = "margen_porcentaje_anterior", precision = 5, scale = 2)
    private BigDecimal margenPorcentajeAnterior;

    @Column(name = "margen_porcentaje_nuevo", precision = 5, scale = 2)
    private BigDecimal margenPorcentajeNuevo;

    // ==================== METADATOS ====================

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio = LocalDateTime.now();

    @Column(name = "fecha_desde", nullable = false)
    private LocalDateTime fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDateTime fechaHasta; // NULL = actualmente vigente

    @Column(name = "usuario_responsable", length = 100)
    private String usuarioResponsable;

    // ==================== MÉTODOS DE VIGENCIA ====================


     //Verifica si este registro está actualmente vigente
    public boolean esVigente() {
        return fechaHasta == null;
    }


     //Marca este registro como no vigente, estableciendo la fecha de fin
    public void marcarComoNoVigente(LocalDateTime fechaFin) {
        this.fechaHasta = fechaFin;
    }


     // Establece este registro como vigente (sin fecha de fin)
    public void marcarComoVigente() {
        this.fechaHasta = null;
    }

    // ==================== MÉTODOS HELPER ====================


     // Calcula automáticamente los márgenes antes de guardar
    @PrePersist
    @PreUpdate
    public void calcularMargenes() {
        // Calcular margen anterior (si hay datos)
        if (precioAnterior != null && precioCostoAnterior != null) {
            this.margenAnterior = precioAnterior.subtract(precioCostoAnterior);
            this.margenPorcentajeAnterior = calcularPorcentaje(margenAnterior, precioAnterior);
        }

        // Calcular margen nuevo (si hay datos)
        if (precioNuevo != null && precioCostoNuevo != null) {
            this.margenNuevo = precioNuevo.subtract(precioCostoNuevo);
            this.margenPorcentajeNuevo = calcularPorcentaje(margenNuevo, precioNuevo);
        }
    }


     //Calcula el porcentaje de margen
     // Fórmula: (margen / precioVenta) * 100
    private BigDecimal calcularPorcentaje(BigDecimal margen, BigDecimal precioVenta) {
        if (precioVenta == null || precioVenta.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return margen.divide(precioVenta, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }


     // Verifica si el margen mejoró o empeoró
    public String getEstadoMargen() {
        if (margenNuevo == null || margenAnterior == null) {
            return "SIN_DATOS";
        }

        int comparacion = margenNuevo.compareTo(margenAnterior);
        if (comparacion > 0) {
            return "MEJORO";
        } else if (comparacion < 0) {
            return "EMPEORO";
        } else {
            return "SIN_CAMBIOS";
        }
    }


     // Obtiene la diferencia de margen
    public BigDecimal getDiferenciaMargen() {
        if (margenNuevo == null || margenAnterior == null) {
            return BigDecimal.ZERO;
        }
        return margenNuevo.subtract(margenAnterior);
    }


     // Obtiene la diferencia de margen en porcentaje
    public BigDecimal getDiferenciaMargenPorcentaje() {
        if (margenPorcentajeNuevo == null || margenPorcentajeAnterior == null) {
            return BigDecimal.ZERO;
        }
        return margenPorcentajeNuevo.subtract(margenPorcentajeAnterior);
    }


     // Verifica si hubo cambio en el precio de venta
    public boolean huboChangePrecioVenta() {
        if (precioNuevo == null || precioAnterior == null) {
            return false;
        }
        return precioNuevo.compareTo(precioAnterior) != 0;
    }


     // Verifica si hubo cambio en el precio de costo
    public boolean huboChangePrecioCosto() {
        if (precioCostoNuevo == null || precioCostoAnterior == null) {
            return false;
        }
        return precioCostoNuevo.compareTo(precioCostoAnterior) != 0;
    }


     // Obtiene el porcentaje de aumento/disminución del precio de venta
    public BigDecimal getPorcentajeCambioPrecioVenta() {
        if (precioNuevo == null || precioAnterior == null ||
                precioAnterior.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal diferencia = precioNuevo.subtract(precioAnterior);
        return diferencia.divide(precioAnterior, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }


     // Obtiene el porcentaje de aumento/disminución del precio de costo
    public BigDecimal getPorcentajeCambioPrecioCosto() {
        if (precioCostoNuevo == null || precioCostoAnterior == null ||
                precioCostoAnterior.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal diferencia = precioCostoNuevo.subtract(precioCostoAnterior);
        return diferencia.divide(precioCostoAnterior, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }
}