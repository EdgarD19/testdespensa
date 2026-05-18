package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Empleado;
import com.proyecto2.DespensaProyect.domain.entity.OrdenCompra;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
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
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {

    List<OrdenCompra> findByProveedor(Proveedor proveedor);

    List<OrdenCompra> findByProveedorIdProveedor(Long idProveedor);

    List<OrdenCompra> findByEmpleado(Empleado empleado);

    List<OrdenCompra> findByEmpleadoIdEmpleado(Long idEmpleado);

    List<OrdenCompra> findByEstado(String estado);

    Page<OrdenCompra> findByEstadoOrderByFechaEmisionDesc(String estado, Pageable pageable);

    List<OrdenCompra> findByFechaEmisionBetween(LocalDate inicio, LocalDate fin);

    @Query("SELECT o FROM OrdenCompra o " +
            "LEFT JOIN FETCH o.proveedor " +
            "LEFT JOIN FETCH o.empleado " +
            "WHERE o.idOrdenCompra = :id")
    Optional<OrdenCompra> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT o FROM OrdenCompra o " +
            "LEFT JOIN FETCH o.detalles d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE o.idOrdenCompra = :id")
    Optional<OrdenCompra> findByIdWithDetalles(@Param("id") Long id);

    @Query("SELECT o FROM OrdenCompra o WHERE " +
            "(:idProveedor IS NULL OR o.proveedor.idProveedor = :idProveedor) AND " +
            "(:estado IS NULL OR o.estado = :estado) AND " +
            "(:fechaInicio IS NULL OR o.fechaEmision >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR o.fechaEmision <= :fechaFin)")
    Page<OrdenCompra> buscarPorCriterios(
            @Param("idProveedor") Long idProveedor,
            @Param("estado") String estado,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            Pageable pageable
    );

    long countByEstado(String estado);
}