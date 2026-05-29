package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rubro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubro")
    private Long idRubro;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}
