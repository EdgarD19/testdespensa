package com.proyecto2.DespensaProyect.model.detailResponse;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String code;
}
