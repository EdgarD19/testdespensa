package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.domain.entity.Factura;
import com.proyecto2.DespensaProyect.domain.entity.Pedido;
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
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByNroFactura(String nroFactura);

    boolean existsByNroFactura(String nroFactura);

    List<Factura> findByCliente(Cliente cliente);

    List<Factura> findByClienteIdCliente(Long idCliente);

    Optional<Factura> findByPedido(Pedido pedido);

    Optional<Factura> findByPedidoIdPedido(Long idPedido);

    List<Factura> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<Factura> findByFechaBetweenOrderByFechaDesc(
            LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    Page<Factura> findByClienteIdClienteOrderByFechaDesc(Long idCliente, Pageable pageable);

    @Query("SELECT f FROM Factura f " +
            "LEFT JOIN FETCH f.cliente " +
            "LEFT JOIN FETCH f.pedido " +
            "WHERE f.idFactura = :id")
    Optional<Factura> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT f FROM Factura f " +
            "LEFT JOIN FETCH f.detalles d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE f.idFactura = :id")
    Optional<Factura> findByIdWithDetalles(@Param("id") Long id);

    @Query("SELECT f FROM Factura f WHERE " +
            "(:nroFactura IS NULL OR f.nroFactura LIKE CONCAT('%', :nroFactura, '%')) AND " +
            "(:idCliente IS NULL OR f.cliente.idCliente = :idCliente) AND " +
            "(:fechaInicio IS NULL OR f.fecha >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR f.fecha <= :fechaFin)")
    Page<Factura> buscarPorCriterios(
            @Param("nroFactura") String nroFactura,
            @Param("idCliente") Long idCliente,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            Pageable pageable
    );

    // Estadísticas
    @Query("SELECT SUM(f.total) FROM Factura f WHERE f.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularTotalFacturadoPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT COUNT(f) FROM Factura f WHERE f.fecha BETWEEN :inicio AND :fin")
    long contarFacturasPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT f FROM Factura f " +
            "WHERE f.fecha BETWEEN :inicio AND :fin " +
            "ORDER BY f.total DESC")
    List<Factura> findFacturasMayores(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            Pageable pageable
    );
}
