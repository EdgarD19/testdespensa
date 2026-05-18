package com.proyecto2.DespensaProyect.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TipoMovimientoResponse {
        private Integer idMovimiento;
        private String nombre;
        private String descripcion;
        private String mensaje;

}
