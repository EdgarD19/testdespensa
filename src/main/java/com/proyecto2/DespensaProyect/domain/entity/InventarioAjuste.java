package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "inventario_ajuste")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioAjuste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ajuste")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "tipo_ajuste", length = 80)
    private String tipoAjuste;

    @Column(name = "fecha_ajuste", nullable = false)
    private LocalDate fechaAjuste;

    @Column(name = "stock_anterior", precision = 12, scale = 2)
    private BigDecimal stockAnterior;

    @Column(name = "nuevo_stock", precision = 12, scale = 2, nullable = false)
    private BigDecimal nuevoStock;

    @Column(name = "diferencia", precision = 12, scale = 2)
    private BigDecimal diferencia;

    @Column(name = "justificacion", columnDefinition = "TEXT")
    private String justificacion;

    @Column(name = "detalle_otro", columnDefinition = "TEXT")
    private String detalleOtro;

    @Column(name = "autorizado_por", length = 200)
    private String autorizadoPor;

    /** AUTORIZADO | PENDIENTE_DE_AUTORIZACION */
    @Column(name = "estado", length = 50, nullable = false)
    private String estado;
}
