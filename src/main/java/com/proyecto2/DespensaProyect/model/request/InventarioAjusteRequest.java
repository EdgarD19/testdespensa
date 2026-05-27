package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventarioAjusteRequest {

    @JsonProperty("idProducto")
    private Long idProducto;

    @JsonProperty("nombreProducto")
    private String nombreProducto;

    @JsonProperty("tipoAjuste")
    private String tipoAjuste;

    @JsonProperty("fechaAjuste")
    private String fechaAjuste;

    @JsonProperty("stockAnterior")
    private BigDecimal stockAnterior;

    @JsonProperty("nuevoStock")
    private BigDecimal nuevoStock;

    @JsonProperty("justificacion")
    private String justificacion;

    @JsonProperty("autorizadoPor")
    private String autorizadoPor;
}
