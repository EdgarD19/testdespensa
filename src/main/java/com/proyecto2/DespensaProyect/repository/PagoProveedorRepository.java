package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.PagoProveedor;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, Long> {

    List<PagoProveedor> findByProveedor(Proveedor proveedor);

    List<PagoProveedor> findByProveedorIdProveedor(Long idProveedor);

    Page<PagoProveedor> findByProveedorIdProveedorOrderByFechaDesc(Long idProveedor, Pageable pageable);

    List<PagoProveedor> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT SUM(p.monto) FROM PagoProveedor p " +
            "WHERE p.proveedor.idProveedor = :idProveedor")
    BigDecimal calcularTotalPagadoProveedor(@Param("idProveedor") Long idProveedor);

    @Query("SELECT SUM(p.monto) FROM PagoProveedor p " +
            "WHERE p.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularTotalPagadoPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);
}
