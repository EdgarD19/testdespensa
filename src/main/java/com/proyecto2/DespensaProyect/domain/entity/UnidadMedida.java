package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"productos"})
@EqualsAndHashCode(exclude = {"productos"})
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long idUnidad;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "abreviatura", length = 10)
    private String abreviatura;

    @OneToMany(mappedBy = "unidadMedida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

}