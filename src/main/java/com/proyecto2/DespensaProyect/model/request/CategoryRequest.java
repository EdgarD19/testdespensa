package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import lombok.*;

import java.io.Serializable;


@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest implements Serializable {

    @JsonProperty("name")
    private String name;


    public CategoriaProducto toEntity(){
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setNombre(this.name);
        return categoria;
    }
}
