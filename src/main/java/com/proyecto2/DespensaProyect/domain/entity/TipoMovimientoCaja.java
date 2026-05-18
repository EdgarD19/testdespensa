package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_movimiento_caja")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movimientosCaja"})
@EqualsAndHashCode(exclude = {"movimientosCaja"})
public class TipoMovimientoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_mov_caja")
    private Long idTipoMovCaja;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @OneToMany(mappedBy = "tipoMovimientoCaja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimientoCaja> movimientosCaja;

}