package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PatchRequest {
    private BigDecimal precio;
    private String nombre;
    private String descripcion;
    private String codigoBarra;
    private Long idSubcategoria;
    private Long idUnidadMedida;
    private Long idProveedor;
    private BigDecimal stockActual;
    private Boolean productoPesable;
    private BigDecimal precioPorKg;
    private Long idMarca;
    private BigDecimal precioCompra;
    private BigDecimal stockMinimo;
    private String contenido;
    private Boolean activo;
}
