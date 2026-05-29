package com.proyecto2.DespensaProyect.mapper;

import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.domain.entity.ProductoProveedor;
import com.proyecto2.DespensaProyect.domain.entity.SubcategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.UnidadMedida;
import com.proyecto2.DespensaProyect.model.detailResponse.ProductResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }
        BigDecimal precio = producto.getPrecio();
        Double price = precio != null ? precio.doubleValue() : null;

        Long idSubcategoria = null;
        String subcategoryName = null;
        Long idCategoria = null;
        String categoryName = null;
        SubcategoriaProducto sub = producto.getSubcategoria();
        if (sub != null) {
            idSubcategoria = sub.getIdSubcategoria();
            subcategoryName = sub.getNombre();
            CategoriaProducto cat = sub.getCategoria();
            if (cat != null) {
                idCategoria = cat.getIdCategoria();
                categoryName = cat.getNombre();
            }
        }

        Long idUnidad = null;
        String unitName = null;
        String unitAbbreviation = null;
        UnidadMedida um = producto.getUnidadMedida();
        if (um != null) {
            idUnidad = um.getIdUnidad();
            unitName = um.getNombre();
            unitAbbreviation = um.getAbreviatura();
        }

        Long idProveedor = null;
        String supplierName = null;
        Optional<ProductoProveedor> ppOpt = firstProveedorActivo(producto.getProveedores());
        if (ppOpt.isPresent() && ppOpt.get().getProveedor() != null) {
            idProveedor = ppOpt.get().getProveedor().getIdProveedor();
            supplierName = ppOpt.get().getProveedor().getNombre();
        }

        Long idMarca = null;
        String marcaName = null;
        if (producto.getMarca() != null) {
            idMarca = producto.getMarca().getIdMarca();
            marcaName = producto.getMarca().getNombre();
        }

        return ProductResponse.builder()
                .id(producto.getIdProducto())
                .name(producto.getNombre())
                .price(price)
                .description(producto.getDescripcion())
                .code(producto.getCodigoBarra())
                .stockActual(producto.getStockActual())
                .idSubcategoria(idSubcategoria)
                .subcategoryName(subcategoryName)
                .idCategoria(idCategoria)
                .categoryName(categoryName)
                .idUnidad(idUnidad)
                .unitName(unitName)
                .unitAbbreviation(unitAbbreviation)
                .idProveedor(idProveedor)
                .supplierName(supplierName)
                .productoPesable(producto.getProductoPesable())
                .precioPorKg(producto.getPrecioPorKg())
                .idMarca(idMarca)
                .marcaName(marcaName)
                .build();
    }

    private static Optional<ProductoProveedor> firstProveedorActivo(List<ProductoProveedor> lista) {
        if (lista == null || lista.isEmpty()) {
            return Optional.empty();
        }
        Optional<ProductoProveedor> activo = lista.stream()
                .filter(pp -> pp != null && Boolean.TRUE.equals(pp.getActivo()))
                .findFirst();
        if (activo.isPresent()) {
            return activo;
        }
        return lista.stream().filter(pp -> pp != null).findFirst();
    }
}
