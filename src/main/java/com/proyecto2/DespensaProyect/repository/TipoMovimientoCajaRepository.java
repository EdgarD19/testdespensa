package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.TipoMovimientoCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoMovimientoCajaRepository extends JpaRepository<TipoMovimientoCaja, Long> {

    Optional<TipoMovimientoCaja> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}