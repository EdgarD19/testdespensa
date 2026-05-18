package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query("SELECT r FROM Rol r " +
            "LEFT JOIN FETCH r.permisos " +
            "WHERE r.idRol = :id")
    Optional<Rol> findByIdWithPermisos(@Param("id") Long id);

    @Query("SELECT r FROM Rol r " +
            "LEFT JOIN FETCH r.permisos " +
            "WHERE r.nombre = :nombre")
    Optional<Rol> findByNombreWithPermisos(@Param("nombre") String nombre);
}
