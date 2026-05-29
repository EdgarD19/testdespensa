package com.proyecto2.DespensaProyect.model.response;

import com.proyecto2.DespensaProyect.model.PageWrapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ProveedorResponse;
import org.springframework.data.domain.Page;

public class ProveedoresResponse extends PageWrapper<ProveedorResponse> {
    public ProveedoresResponse(Page<ProveedorResponse> page) {
        super(page);
    }
}
