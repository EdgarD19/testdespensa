package com.proyecto2.DespensaProyect.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineaVentaRequest {

    @NotNull
    private Long idProducto;

    @NotNull
    @Positive
    private BigDecimal cantidad;

    @NotNull
    @Positive
    private BigDecimal precioUnitario;

    /** Opcional; el servidor puede recalcular cantidad × precioUnitario */
    private BigDecimal subtotal;
}
