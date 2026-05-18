package com.proyecto2.DespensaProyect.repository.specification;

import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {

    public static Specification<Cliente> hasSearch(String search) {
        return (root, query, criteriaBuilder) -> {
            /*
             * No usar fetch de colecciones (contactos) aquí: con paginación, Hibernate
             * puede fallar o paginar en memoria. Los contactos se cargan en lazy dentro
             * del mismo @Transactional al mapear a ClientResponse.
             */
            if (search == null || search.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String pattern = "%" + search.toLowerCase() + "%";

            var ciudadJoin = root.join("ciudad", JoinType.LEFT);

            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("documentNumber")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(ciudadJoin.get("nombre")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("gender")), pattern)
            );
        };
    }
}

