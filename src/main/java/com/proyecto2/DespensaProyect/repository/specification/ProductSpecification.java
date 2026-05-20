package com.proyecto2.DespensaProyect.repository.specification;

import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Producto> hasSearch(String search) {
        return (root, query, cb) -> {
            if (search == null || search.trim().isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("nombre")), pattern),
                    cb.like(cb.lower(root.get("codigoBarra")), pattern),
                    cb.like(cb.lower(root.get("descripcion")), pattern)
            );
        };
    }

    public static Specification<Producto> hasCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("categoria").get("idCategoria"), categoryId);
        };
    }
}
