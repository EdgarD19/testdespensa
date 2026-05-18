package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Empleado;
import com.proyecto2.DespensaProyect.domain.entity.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByEmpleado(Empleado empleado);

    List<Inventario> findByEmpleadoIdEmpleado(Long idEmpleado);

    List<Inventario> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Inventario> findByAjusteRealizado(Boolean ajusteRealizado);

    Page<Inventario> findByOrderByFechaDesc(Pageable pageable);

    @Query("SELECT i FROM Inventario i " +
            "LEFT JOIN FETCH i.empleado " +
            "WHERE i.idInventario = :id")
    Optional<Inventario> findByIdWithEmpleado(@Param("id") Long id);

    @Query("SELECT i FROM Inventario i " +
            "LEFT JOIN FETCH i.detalles " +
            "WHERE i.idInventario = :id")
    Optional<Inventario> findByIdWithDetalles(@Param("id") Long id);
}