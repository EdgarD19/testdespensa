package com.proyecto2.DespensaProyect.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Getter
@Builder
@Data
public class CategoryResponse {
    private String nombre;
    private Long id;

}
