package com.proyecto2.DespensaProyect.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaestroItemResponse {
    private Long id;
    private String nombre;
    private String abreviatura;
    private String descripcion;
}
