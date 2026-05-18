package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.DetalleFactura;
import com.proyecto2.DespensaProyect.domain.entity.Factura;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    List<DetalleFactura> findByFactura(Factura factura);

    List<DetalleFactura> findByFacturaIdFactura(Long idFactura);

    List<DetalleFactura> findByProducto(Producto producto);

    List<DetalleFactura> findByProducto(Long idProducto);

    @Query("SELECT d FROM DetalleFactura d " +
            "LEFT JOIN FETCH d.factura " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE d.factura.idFactura = :idFactura")
    List<DetalleFactura> findByFacturaWithProductos(@Param("idFactura") Long idFactura);

    // Estadísticas de ventas por producto
    @Query("SELECT d.producto, SUM(d.cantidad) as totalVendido, SUM(d.cantidad * d.precioUnitario) as totalMonto " +
            "FROM DetalleFactura d " +
            "WHERE d.factura.fecha BETWEEN :inicio AND :fin " +
            "GROUP BY d.producto " +
            "ORDER BY totalMonto DESC")
    List<Object[]> findEstadisticasVentasPorProducto(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT SUM(d.cantidad * d.precioUnitario) FROM DetalleFactura d " +
            "WHERE d.producto.idProducto = :idProducto " +
            "AND d.factura.fecha BETWEEN :inicio AND :fin")
    BigDecimal calcularVentasTotalesProducto(
            @Param("idProducto") Long idProducto,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}