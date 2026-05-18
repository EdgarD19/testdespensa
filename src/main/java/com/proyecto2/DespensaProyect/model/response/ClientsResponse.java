package com.proyecto2.DespensaProyect.model.response;

import com.proyecto2.DespensaProyect.model.PageWrapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ClientResponse;
import org.springframework.data.domain.Page;

public class ClientsResponse extends PageWrapper<ClientResponse> {
    public ClientsResponse(Page<ClientResponse> page){
        super(page);
    }
}
