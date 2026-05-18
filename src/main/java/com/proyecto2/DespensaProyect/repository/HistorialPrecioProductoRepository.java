package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.HistorialPrecioProducto;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialPrecioProductoRepository extends JpaRepository<HistorialPrecioProducto, Long> {

    // ==================== BÚSQUEDAS BÁSICAS ====================

    List<HistorialPrecioProducto> findByProducto(Producto producto);

    List<HistorialPrecioProducto> findByProductoIdProducto(Long idProducto);

    List<HistorialPrecioProducto> findByProductoIdProductoOrderByFechaCambioDesc(Long idProducto);

    List<HistorialPrecioProducto> findByProveedor(Proveedor proveedor);

    List<HistorialPrecioProducto> findByProveedorIdProveedor(Long idProveedor);

    List<HistorialPrecioProducto> findByFechaCambioBetween(
            LocalDateTime inicio, LocalDateTime fin);

    // ==================== CONSULTAS AVANZADAS ====================

    /**
     * Obtener historial completo de un producto con detalles
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "ORDER BY h.fechaCambio DESC")
    List<HistorialPrecioProducto> findHistorialByProductoOrderByFecha(
            @Param("idProducto") Long idProducto);

    /**
     * Obtener el último cambio de precio de un producto
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "ORDER BY h.fechaCambio DESC " +
            "LIMIT 1")
    Optional<HistorialPrecioProducto> findUltimoCambioByProducto(
            @Param("idProducto") Long idProducto);

    /**
     * Obtener historial de un producto con un proveedor específico
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "AND h.proveedor.idProveedor = :idProveedor " +
            "ORDER BY h.fechaCambio DESC")
    List<HistorialPrecioProducto> findByProductoAndProveedor(
            @Param("idProducto") Long idProducto,
            @Param("idProveedor") Long idProveedor);

    /**
     * Productos cuyo margen ha disminuido recientemente
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.margenNuevo < h.margenAnterior " +
            "AND h.fechaCambio >= :fechaDesde " +
            "ORDER BY h.fechaCambio DESC")
    List<HistorialPrecioProducto> findProductosConMargenDisminuido(
            @Param("fechaDesde") LocalDateTime fechaDesde);

    /**
     * Productos cuyo margen ha mejorado recientemente
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.margenNuevo > h.margenAnterior " +
            "AND h.fechaCambio >= :fechaDesde " +
            "ORDER BY h.fechaCambio DESC")
    List<HistorialPrecioProducto> findProductosConMargenMejorado(
            @Param("fechaDesde") LocalDateTime fechaDesde);

    /**
     * Estadísticas de precios de un producto
     */
    @Query("SELECT " +
            "MIN(h.precioNuevo) as precioMinimo, " +
            "MAX(h.precioNuevo) as precioMaximo, " +
            "AVG(h.precioNuevo) as precioPromedio, " +
            "COUNT(h) as cantidadCambios " +
            "FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto")
    Object[] getEstadisticasPrecio(@Param("idProducto") Long idProducto);

    /**
     * Cambios de precio en un rango de fechas
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.fechaCambio BETWEEN :inicio AND :fin " +
            "ORDER BY h.fechaCambio DESC")
    List<HistorialPrecioProducto> findCambiosPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    /**
     * Productos con mayor variación de precio
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE ABS(h.precioNuevo - h.precioAnterior) > :umbral " +
            "ORDER BY ABS(h.precioNuevo - h.precioAnterior) DESC")
    List<HistorialPrecioProducto> findProductosConMayorVariacion(
            @Param("umbral") BigDecimal umbral);

    /**
     * Contar cambios de precio de un producto
     */
    long countByProductoIdProducto(Long idProducto);

    /**
     * Verificar si un producto tuvo cambios recientes
     */
    boolean existsByProductoIdProductoAndFechaCambioAfter(
            Long idProducto, LocalDateTime fecha);

    // ==================== CONSULTAS POR VIGENCIA ====================

    /**
     * Obtener el precio vigente actualmente para un producto (y opcionalmente un proveedor)
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "AND (:idProveedor IS NULL OR h.proveedor.idProveedor = :idProveedor) " +
            "AND h.fechaHasta IS NULL " +
            "ORDER BY h.fechaCambio DESC")
    Optional<HistorialPrecioProducto> findPrecioVigenteByProducto(
            @Param("idProducto") Long idProducto,
            @Param("idProveedor") Long idProveedor);

    /**
     * Obtener el precio que estaba vigente en una fecha específica
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "AND (:idProveedor IS NULL OR h.proveedor.idProveedor = :idProveedor) " +
            "AND h.fechaDesde <= :fecha " +
            "AND (h.fechaHasta IS NULL OR h.fechaHasta >= :fecha) " +
            "ORDER BY h.fechaDesde DESC")
    Optional<HistorialPrecioProducto> findPrecioPorFecha(
            @Param("idProducto") Long idProducto,
            @Param("idProveedor") Long idProveedor,
            @Param("fecha") LocalDateTime fecha);

    /**
     * Obtener todos los registros vigentes (fecha_hasta IS NULL)
     */
    List<HistorialPrecioProducto> findByFechaHastaIsNull();

    /**
     * Obtener histórico completo con información de vigencia
     */
    @Query("SELECT h FROM HistorialPrecioProducto h " +
            "WHERE h.producto.idProducto = :idProducto " +
            "ORDER BY h.fechaDesde DESC")
    List<HistorialPrecioProducto> findHistorialCompletoByProducto(
            @Param("idProducto") Long idProducto);
}