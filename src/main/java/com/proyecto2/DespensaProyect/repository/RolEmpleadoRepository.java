package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.RolEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolEmpleadoRepository extends JpaRepository<RolEmpleado, Long> {

    Optional<RolEmpleado> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
