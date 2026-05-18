package com.proyecto2.DespensaProyect.model.response;

import com.proyecto2.DespensaProyect.model.PageWrapper;
import com.proyecto2.DespensaProyect.model.detailResponse.MovimientoInventarioDetailResponse;
import org.springframework.data.domain.Page;

public class MovimientosInventarioResponse extends PageWrapper<MovimientoInventarioDetailResponse> {
    public MovimientosInventarioResponse(Page<MovimientoInventarioDetailResponse> page){super(page);}
}
