package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VentaFacturaRequest {

    private String fechaFactura;

    private String tipoFactura;

    private String estado;

    private Long idCliente;

    private String etiquetaCliente;

    @NotEmpty
    @Valid
    private List<LineaVentaRequest> lineas;

    @NotNull
    private BigDecimal total;

    private BigDecimal montoPagado;

    private BigDecimal cambio;

    @JsonProperty("formaPago")
    private String formaPago;
}
