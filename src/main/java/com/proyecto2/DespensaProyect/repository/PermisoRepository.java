package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PermisoRepository extends JpaRepository<Permisos, Long> {

    Optional<Permisos> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<Permisos> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Permisos p " +
            "JOIN p.roles r " +
            "WHERE r.idRol = :idRol")
    Set<Permisos> findPermisosByRol(@Param("idRol") Long idRol);

    @Query("SELECT p FROM Permisos p " +
            "JOIN p.roles r " +
            "JOIN r.usuarios u " +
            "WHERE u.idUsuario = :idUsuario")
    Set<Permisos> findPermisosByUsuario(@Param("idUsuario") Long idUsuario);
}