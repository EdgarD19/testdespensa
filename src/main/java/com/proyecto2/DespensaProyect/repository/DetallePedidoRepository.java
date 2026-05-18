package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.DetallePedido;
import com.proyecto2.DespensaProyect.domain.entity.Pedido;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    List<DetallePedido> findByPedido(Pedido pedido);

    List<DetallePedido> findByPedidoIdPedido(Long idPedido);

    List<DetallePedido> findByProducto(Producto producto);

    List<DetallePedido> findByProducto(Long idProducto);

    @Query("SELECT d FROM DetallePedido d " +
            "LEFT JOIN FETCH d.pedido " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE d.pedido.idPedido = :idPedido")
    List<DetallePedido> findByPedidoWithProductos(@Param("idPedido") Long idPedido);

    // Productos más vendidos
    @Query("SELECT d.producto, SUM(d.cantidad) as totalVendido " +
            "FROM DetallePedido d " +
            "WHERE d.pedido.fecha BETWEEN :inicio AND :fin " +
            "GROUP BY d.producto " +
            "ORDER BY totalVendido DESC")
    List<Object[]> findProductosMasVendidos(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT SUM(d.cantidad) FROM DetallePedido d " +
            "WHERE d.producto.idProducto = :idProducto")
    BigDecimal calcularTotalVendidoProducto(@Param("idProducto") Long idProducto);
}
