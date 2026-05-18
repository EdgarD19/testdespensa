package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_movimiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movimientosInventario"})
@EqualsAndHashCode(exclude = {"movimientosInventario"})
public class TipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento")
    private Long idTipoMovimiento;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;


    @Column(name = "descripcion" , length = 200)
    private String description;

    @OneToMany(mappedBy = "tipoMovimiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientosInventario;

    public TipoMovimiento(Long idTipoMovimiento, String nombre) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
    }
}