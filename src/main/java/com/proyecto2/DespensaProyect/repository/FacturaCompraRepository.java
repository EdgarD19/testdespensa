package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.FacturaCompra;
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
import java.util.Optional;

@Repository
public interface FacturaCompraRepository extends JpaRepository<FacturaCompra, Long> {

    List<FacturaCompra> findByProveedor(Proveedor proveedor);

    List<FacturaCompra> findByProveedorIdProveedor(Long idProveedor);

    List<FacturaCompra> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<FacturaCompra> findByFechaBetweenOrderByFechaDesc(
            LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    Page<FacturaCompra> findByProveedorIdProveedorOrderByFechaDesc(Long idProveedor, Pageable pageable);

    @Query("SELECT f FROM FacturaCompra f " +
            "LEFT JOIN FETCH f.proveedor " +
            "WHERE f.idFacturaCompra = :id")
    Optional<FacturaCompra> findByIdWithProveedor(@Param("id") Long id);

    @Query("SELECT f FROM FacturaCompra f " +
            "LEFT JOIN FETCH f.detalles d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE f.idFacturaCompra = :id")
    Optional<FacturaCompra> findByIdWithDetalles(@Param("id") Long id);

    @Query("SELECT SUM(f.total) FROM FacturaCompra f WHERE f.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularTotalComprasPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT SUM(f.total) FROM FacturaCompra f " +
            "WHERE f.proveedor.idProveedor = :idProveedor")
    BigDecimal calcularTotalComprasProveedor(@Param("idProveedor") Long idProveedor);
}

