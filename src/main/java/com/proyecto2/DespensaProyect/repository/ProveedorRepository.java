package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    Optional<Proveedor> findByNombre(String nombre);

    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);

    Page<Proveedor> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    boolean existsByNombre(String nombre);

    @Query("SELECT p FROM Proveedor p ORDER BY p.nombre ASC")
    List<Proveedor> findAllOrderByNombre();

    @Query("SELECT p FROM Proveedor p " +
            "LEFT JOIN FETCH p.contactos " +
            "WHERE p.idProveedor = :id")
    Optional<Proveedor> findByIdWithContactos(@Param("id") Long id);
}

