package com.proyecto2.DespensaProyect.mapper;


import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.model.detailResponse.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }
        return ProductResponse.builder()
                .id(producto.getIdProducto())
                .name(producto.getNombre())
                .price(producto.getPrecio().doubleValue())
                .description(producto.getDescripcion())
                .code(producto.getCodigoBarra())
                .build();
    }

}
