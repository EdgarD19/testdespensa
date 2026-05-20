package com.proyecto2.DespensaProyect.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaFacturaResponse {

    @JsonProperty("idFactura")
    private Long idFactura;

    @JsonProperty("numeroFactura")
    private String numeroFactura;
}
