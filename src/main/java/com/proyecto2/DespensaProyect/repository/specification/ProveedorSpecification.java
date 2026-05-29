package com.proyecto2.DespensaProyect.repository.specification;

import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProveedorSpecification {

    public static Specification<Proveedor> hasSearch(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";

            var ciudadJoin = root.join("ciudad", JoinType.LEFT);
            var paisJoin = root.join("pais", JoinType.LEFT);

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("apellido")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("numeroDocumento")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("personaContacto")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("telefono")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(ciudadJoin.get("nombre")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(paisJoin.get("nombre")), pattern)
            );
        };
    }
}
