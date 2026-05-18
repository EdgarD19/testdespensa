package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.domain.entity.Empleado;
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
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

    List<Pedido> findByClienteIdCliente(Long idCliente);

    List<Pedido> findByEmpleado(Empleado empleado);

    List<Pedido> findByEmpleadoIdEmpleado(Long idEmpleado);

    List<Pedido> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<Pedido> findByFechaBetweenOrderByFechaDesc(
            LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    List<Pedido> findByTotalGreaterThanEqual(BigDecimal montoMinimo);

    Page<Pedido> findByClienteIdClienteOrderByFechaDesc(Long idCliente, Pageable pageable);

    @Query("SELECT p FROM Pedido p " +
            "LEFT JOIN FETCH p.cliente " +
            "LEFT JOIN FETCH p.empleado " +
            "WHERE p.idPedido = :id")
    Optional<Pedido> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT p FROM Pedido p " +
            "LEFT JOIN FETCH p.detalles d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE p.idPedido = :id")
    Optional<Pedido> findByIdWithDetalles(@Param("id") Long id);

    @Query("SELECT p FROM Pedido p WHERE " +
            "(:idCliente IS NULL OR p.cliente.idCliente = :idCliente) AND " +
            "(:idEmpleado IS NULL OR p.empleado.idEmpleado = :idEmpleado) AND " +
            "(:fechaInicio IS NULL OR p.fecha >= :fechaInicio) AND " +
            "(:fechaFin IS NULL OR p.fecha <= :fechaFin) AND " +
            "(:montoMin IS NULL OR p.total >= :montoMin) AND " +
            "(:montoMax IS NULL OR p.total <= :montoMax)")
    Page<Pedido> buscarPorCriterios(
            @Param("idCliente") Long idCliente,
            @Param("idEmpleado") Long idEmpleado,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("montoMin") BigDecimal montoMin,
            @Param("montoMax") BigDecimal montoMax,
            Pageable pageable
    );

    // Estadísticas
    @Query("SELECT SUM(p.total) FROM Pedido p WHERE p.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularTotalVentasPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.fecha BETWEEN :inicio AND :fin")
    long contarPedidosPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT AVG(p.total) FROM Pedido p WHERE p.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularTicketPromedioPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);
}
