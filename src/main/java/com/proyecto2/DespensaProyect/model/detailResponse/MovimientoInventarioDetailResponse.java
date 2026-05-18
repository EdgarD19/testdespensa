package com.proyecto2.DespensaProyect.model.detailResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovimientoInventarioDetailResponse {


    private Long id;
    private String producto;
    private String tipoMovimiento;
    private Date fecha;
    private String referencia;
}
