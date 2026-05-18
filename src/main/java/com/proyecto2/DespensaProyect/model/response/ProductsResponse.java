package com.proyecto2.DespensaProyect.model.response;

import com.proyecto2.DespensaProyect.model.PageWrapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ProductResponse;
import org.springframework.data.domain.Page;

public class ProductsResponse extends PageWrapper<ProductResponse> {
    public ProductsResponse(Page<ProductResponse> page) {
        super(page);
    }
}
