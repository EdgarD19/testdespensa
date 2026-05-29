package com.proyecto2.DespensaProyect.model.detailResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String code;

    /** stock_actual */
    private BigDecimal stockActual;

    private Long idSubcategoria;
    private String subcategoryName;

    private Long idCategoria;
    private String categoryName;

    private Long idUnidad;
    private String unitName;
    private String unitAbbreviation;

    private Long idProveedor;
    private String supplierName;

    private Boolean productoPesable;
    private BigDecimal precioPorKg;

    private Long idMarca;
    private String marcaName;
}
