package com.proyecto2.DespensaProyect.repository.specification;

import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Producto> hasSearch(String search) {
        return (root, query, cb) -> {
            if (search == null || search.trim().isEmpty()) {
                return cb.conjunction();                        //Siempre verdadera,  No filtra nada,  Permite que otras Specification se sigan aplicando
            }

            String pattern = "%" + search.toLowerCase() + "%";      //convierte a minuscula para preparar para un %like% en la busqueda
            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("codigoBarra")), pattern),
                    cb.like(cb.lower(root.get("description")), pattern)
            );
        };
    }

    public static Specification<Producto> hasCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

}
