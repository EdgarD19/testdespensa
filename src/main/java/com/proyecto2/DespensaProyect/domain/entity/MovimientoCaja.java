package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_caja")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"caja", "tipoMovimientoCaja"})
@EqualsAndHashCode(exclude = {"caja", "tipoMovimientoCaja"})
public class MovimientoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov_caja")
    private Long idMovCaja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
    private Caja caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_mov_caja", referencedColumnName = "id_tipo_mov_caja")
    private TipoMovimientoCaja tipoMovimientoCaja;

    @Column(name = "monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "fecha")
    private LocalDateTime fecha = LocalDateTime.now();
}