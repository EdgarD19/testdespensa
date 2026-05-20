package com.proyecto2.DespensaProyect.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventarioAjusteResponse {

    private Long id;

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

    private BigDecimal diferencia;

    private String justificacion;

    @JsonProperty("detalleOtro")
    private String detalleOtro;

    @JsonProperty("autorizadoPor")
    private String autorizadoPor;

    private String estado;
}
