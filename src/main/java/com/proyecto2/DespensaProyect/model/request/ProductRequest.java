package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductRequest implements Serializable {

    @NotBlank
    @JsonProperty("name")
    private String nombre;

    @JsonProperty("descripcion")
    private String descripcion;

    @NotNull
    @Positive
    @JsonProperty("precio")
    private BigDecimal precio;

    @JsonProperty("id_subcategoria")
    private Long idSubcategoria;

    @NotNull
    @JsonProperty("id_unidad")
    private Long idUnidadMedida;

    @NotNull
    @JsonProperty("id_proveedor")
    private Long idProveedor;

    @NotNull
    @JsonProperty("stock_actual")
    private BigDecimal stockActual;

    @JsonProperty("codigo_producto")
    @Pattern(regexp = "^[0-9]+$", message = "Solo números")
    @Size(min = 8, max = 13, message = "Debe tener entre 8 y 13 dígitos")
    private String codigoBarra;

    @JsonProperty("id_marca")
    private Long idMarca;

    @JsonProperty("precio_compra")
    private BigDecimal precioCompra;

    @JsonProperty("stock_minimo")
    private BigDecimal stockMinimo;

    @JsonProperty("contenido")
    private String contenido;

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("producto_pesable")
    private Boolean productoPesable;

    @JsonProperty("precio_por_kg")
    private BigDecimal precioPorKg;

    public Producto toEntity(){
        Producto producto = new Producto();
        producto.setNombre(this.getNombre());
        producto.setPrecio(this.getPrecio());
        producto.setCodigoBarra(this.getCodigoBarra());
        producto.setStockActual(this.getStockActual());
        producto.setProductoPesable(this.getProductoPesable() != null && this.getProductoPesable());
        return producto;
    }
}
