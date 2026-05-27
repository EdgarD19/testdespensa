package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_precio_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"producto", "proveedor"})
@EqualsAndHashCode(exclude = {"producto", "proveedor"})
public class HistorialPrecioProducto {

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

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio = LocalDateTime.now();

    @Column(name = "precio_costo_anterior", precision = 12, scale = 2)
    private BigDecimal precioCostoAnterior;

    @Column(name = "precio_costo_nuevo", precision = 12, scale = 2)
    private BigDecimal precioCostoNuevo;

    @Column(name = "precio_anterior", precision = 12, scale = 2)
    private BigDecimal precioAnterior;

    @Column(name = "precio_nuevo", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioNuevo;

    @Column(name = "margen_anterior", precision = 12, scale = 2)
    private BigDecimal margenAnterior;

    @Column(name = "margen_nuevo", precision = 12, scale = 2)
    private BigDecimal margenNuevo;

    @Column(name = "usuario_responsable", length = 100)
    private String usuarioResponsable;
}