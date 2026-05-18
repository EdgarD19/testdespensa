package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {

    Optional<UnidadMedida> findByNombre(String nombre);

    Optional<UnidadMedida> findByAbreviatura(String abreviatura);

    boolean existsByNombre(String nombre);

    boolean existsByAbreviatura(String abreviatura);

    @Query("SELECT u FROM UnidadMedida u ORDER BY u.nombre ASC")
    List<UnidadMedida> findAllOrderByNombre();
}
