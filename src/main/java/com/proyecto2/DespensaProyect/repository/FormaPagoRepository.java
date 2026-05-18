package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Long> {

    Optional<FormaPago> findByDescripcion(String descripcion);

    boolean existsByDescripcion(String descripcion);

    List<FormaPago> findByDescripcionContainingIgnoreCase(String descripcion);
}