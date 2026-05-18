package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "forma_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pago")
    private Long idFormaPago;

    @Column(name = "descripcion", length = 100)
    private String descripcion;


}