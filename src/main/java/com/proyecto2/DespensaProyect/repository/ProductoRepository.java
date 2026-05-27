package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>,
        JpaSpecificationExecutor<Producto> {

    // ==================== BÚSQUEDA POR CÓDIGO DE BARRAS ====================

    Optional<Producto> findByCodigoBarra(@Param("codigoBarra") String codigoBarra);

    // ==================== BÚSQUEDAS BÁSICAS ====================

    /**
     * Buscar productos por nombre (case-insensitive, búsqueda parcial)
     */
    List<Producto> findBynombreContainingIgnoreCase(String nombre);

    /**
     * Buscar productos por nombre con paginación
     */
    Page<Producto> findBynombreContainingIgnoreCase(String nombre, Pageable pageable);

    /**
     * Buscar por categoría ID (a través de subcategoria)
     */
    @Query("SELECT p FROM Producto p WHERE p.subcategoria.categoria.idCategoria = :idCategoria")
    List<Producto> findBycategoria_IdCategoria(@Param("idCategoria") Long categoriaId);

    // ==================== BÚSQUEDAS POR PROVEEDOR (NUEVAS - VÍA PRODUCTO_PROVEEDOR) ====================

    /**
     * Buscar productos por proveedor (a través de ProductoProveedor)
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "JOIN p.proveedores pp " +
            "WHERE pp.proveedor.idProveedor = :idProveedor " +
            "AND pp.activo = true")
    List<Producto> findByProveedorId(@Param("idProveedor") Long idProveedor);

    /**
     * Buscar productos por proveedor con paginación
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "JOIN p.proveedores pp " +
            "WHERE pp.proveedor.idProveedor = :idProveedor " +
            "AND pp.activo = true")
    Page<Producto> findByProveedorId(@Param("idProveedor") Long idProveedor, Pageable pageable);

    /**
     * Buscar productos por categoría y proveedor
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "JOIN p.subcategoria s " +
            "JOIN p.proveedores pp " +
            "WHERE s.categoria.idCategoria = :idCategoria " +
            "AND pp.proveedor.idProveedor = :idProveedor " +
            "AND pp.activo = true")
    List<Producto> findByCategoriaAndProveedor(
            @Param("idCategoria") Long idCategoria,
            @Param("idProveedor") Long idProveedor);

    // ==================== BÚSQUEDAS POR STOCK ====================

    /**
     * Productos con stock menor al especificado
     */
    List<Producto> findBystockActualLessThan(BigDecimal stock);

    /**
     * Productos con stock menor o igual al especificado
     */
    List<Producto> findBystockActualLessThanEqual(BigDecimal stock);

    /**
     * Productos sin stock (stock = 0)
     */
    @Query("SELECT p FROM Producto p WHERE p.stockActual = 0")
    List<Producto> findProductosSinStock();

    /**
     * Productos con stock disponible (stock > 0)
     */
    @Query("SELECT p FROM Producto p WHERE p.stockActual > 0")
    List<Producto> findProductosConStock();

    /**
     * Productos con stock disponible, paginado
     */
    @Query("SELECT p FROM Producto p WHERE p.stockActual > 0")
    Page<Producto> findProductosConStock(Pageable pageable);

    // ==================== BÚSQUEDAS AVANZADAS ====================

    /**
     * Buscar productos con todas sus relaciones cargadas (para detalles completos)
     * NOTA: Ahora sin proveedor directo, ya que es relación many-to-many
     */
    @Query("SELECT p FROM Producto p " +
            "LEFT JOIN FETCH p.subcategoria s " +
            "LEFT JOIN FETCH s.categoria " +
            "LEFT JOIN FETCH p.unidadMedida " +
            "WHERE p.idProducto = :id")
    Optional<Producto> findByIdWithDetails(@Param("id") Long id);

    /**
     * Buscar producto con proveedores activos
     */
    @Query("SELECT p FROM Producto p " +
            "LEFT JOIN FETCH p.subcategoria s " +
            "LEFT JOIN FETCH s.categoria " +
            "LEFT JOIN FETCH p.unidadMedida " +
            "LEFT JOIN FETCH p.proveedores pp " +
            "LEFT JOIN FETCH pp.proveedor " +
            "WHERE p.idProducto = :id " +
            "AND (pp.activo = true OR pp IS NULL)")
    Optional<Producto> findByIdWithProveedores(@Param("id") Long id);

    /**
     * Buscar productos por múltiples criterios
     * ACTUALIZADO: Sin proveedor directo
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "LEFT JOIN p.subcategoria s " +
            "LEFT JOIN p.proveedores pp " +
            "WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:idCategoria IS NULL OR s.categoria.idCategoria = :idCategoria) " +
            "AND (:idProveedor IS NULL OR (pp.proveedor.idProveedor = :idProveedor AND pp.activo = true))")
    Page<Producto> buscarPorCriterios(
            @Param("nombre") String nombre,
            @Param("idCategoria") Long idCategoria,
            @Param("idProveedor") Long idProveedor,
            Pageable pageable
    );

    /**
     * Buscar productos con stock bajo un valor mínimo especificado
     */
    @Query("SELECT p FROM Producto p WHERE p.stockActual < :stockMinimo")
    List<Producto> findProductosStockBajo(@Param("stockMinimo") BigDecimal stockMinimo);

    /**
     * Buscar productos con stock bajo un valor mínimo, paginado
     */
    @Query("SELECT p FROM Producto p WHERE p.stockActual < :stockMinimo")
    Page<Producto> findProductosStockBajo(@Param("stockMinimo") BigDecimal stockMinimo, Pageable pageable);

    // ==================== BÚSQUEDAS POR RANGO DE PRECIO ====================

    /**
     * Productos dentro de un rango de precios
     */
    List<Producto> findByprecioBetween(BigDecimal precioMin, BigDecimal precioMax);

    /**
     * Productos dentro de un rango de precios, paginado
     */
    Page<Producto> findByprecioBetween(BigDecimal precioMin, BigDecimal precioMax, Pageable pageable);

    /**
     * Productos con precio mayor o igual al especificado
     */
    List<Producto> findByprecioGreaterThanEqual(BigDecimal precio);

    /**
     * Productos con precio menor o igual al especificado
     */
    List<Producto> findByprecioLessThanEqual(BigDecimal precio);

    // ==================== ESTADÍSTICAS Y REPORTES ====================

    /**
     * Contar productos por categoría
     */
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.subcategoria.categoria.idCategoria = :idCategoria")
    long countBycategoria_IdCategoria(@Param("idCategoria") Long idCategoria);

    /**
     * Contar productos por proveedor (ACTUALIZADO)
     */
    @Query("SELECT COUNT(DISTINCT p) FROM Producto p " +
            "JOIN p.proveedores pp " +
            "WHERE pp.proveedor.idProveedor = :idProveedor " +
            "AND pp.activo = true")
    long countByProveedorId(@Param("idProveedor") Long idProveedor);

    /**
     * Contar productos sin stock
     */
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stockActual = 0")
    long countProductosSinStock();

    /**
     * Contar productos con stock bajo
     */
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stockActual < :stockMinimo")
    long countProductosStockBajo(@Param("stockMinimo") BigDecimal stockMinimo);

    /**
     * Obtener valor total del inventario
     */
    @Query("SELECT SUM(p.precio * p.stockActual) FROM Producto p")
    BigDecimal calcularValorTotalInventario();

    /**
     * Obtener valor total del inventario por categoría
     */
    @Query("SELECT SUM(p.precio * p.stockActual) FROM Producto p " +
            "JOIN p.subcategoria s WHERE s.categoria.idCategoria = :idCategoria")
    BigDecimal calcularValorInventarioPorCategoria(@Param("idCategoria") Long idCategoria);

    /**
     * Productos más caros (top N)
     */
    List<Producto> findTop10ByOrderByPrecioDesc();

    /**
     * Productos más baratos (top N)
     */
    List<Producto> findTop10ByOrderByPrecioAsc();

    // ==================== OPERACIONES DE ACTUALIZACIÓN ====================

    /**
     * Actualizar stock de un producto
     */
    @Modifying
    @Query("UPDATE Producto p SET p.stockActual = p.stockActual + :cantidad " +
            "WHERE p.idProducto = :id")
    int incrementarStock(@Param("id") Long id, @Param("cantidad") BigDecimal cantidad);

    /**
     * Actualizar precio de un producto
     */
    @Modifying
    @Query("UPDATE Producto p SET p.precio = :precio WHERE p.idProducto = :id")
    int actualizarPrecio(@Param("id") Long id, @Param("precio") BigDecimal precio);

    // ==================== EXISTENCIA Y VALIDACIONES ====================

    /**
     * Verificar si existe producto por nombre exacto
     */
    boolean existsBynombre(String nombre);

    /**
     * Verificar si existe producto por nombre (case-insensitive)
     */
    boolean existsBynombreIgnoreCase(String nombre);

    /**
     * Verificar si existe producto por código de barras
     */
    boolean existsByCodigoBarra(String codigoBarra);

    /**
     * Verificar si hay productos en una categoría
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Producto p WHERE p.subcategoria.categoria.idCategoria = :idCategoria")
    boolean existsBycategoria_IdCategoria(@Param("idCategoria") Long idCategoria);

    /**
     * Verificar si hay productos de un proveedor (ACTUALIZADO)
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Producto p " +
            "JOIN p.proveedores pp " +
            "WHERE pp.proveedor.idProveedor = :idProveedor " +
            "AND pp.activo = true")
    boolean existsByProveedorId(@Param("idProveedor") Long idProveedor);

    // ==================== BÚSQUEDA COMPLEJA (FILTROS MÚLTIPLES) ====================

    /**
     * Búsqueda avanzada con múltiples filtros opcionales
     * ACTUALIZADO: Sin proveedor directo
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "LEFT JOIN p.subcategoria s " +
            "LEFT JOIN s.categoria c " +
            "LEFT JOIN p.proveedores pp " +
            "LEFT JOIN pp.proveedor pr " +
            "WHERE (:termino IS NULL OR " +
            "       LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "       LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :termino, '%'))) " +
            "AND (:idCategoria IS NULL OR c.idCategoria = :idCategoria) " +
            "AND (:idProveedor IS NULL OR (pr.idProveedor = :idProveedor AND pp.activo = true)) " +
            "AND (:precioMin IS NULL OR p.precio >= :precioMin) " +
            "AND (:precioMax IS NULL OR p.precio <= :precioMax) " +
            "AND (:stockMin IS NULL OR p.stockActual >= :stockMin) " +
            "AND (:stockMax IS NULL OR p.stockActual <= :stockMax)")
    Page<Producto> busquedaAvanzada(
            @Param("termino") String termino,
            @Param("idCategoria") Long idCategoria,
            @Param("idProveedor") Long idProveedor,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax,
            @Param("stockMin") BigDecimal stockMin,
            @Param("stockMax") BigDecimal stockMax,
            Pageable pageable
    );

    // ==================== QUERIES NATIVAS (SI NECESITAS) ====================

    /**
     * Ejemplo de query nativa para operaciones complejas
     * ACTUALIZADO: Corregido nombre de tabla
     */
    @Query(value = "SELECT p.* FROM despensa.producto p " +
            "INNER JOIN despensa.subcategoria_producto sc ON p.id_subcategoria = sc.id_subcategoria " +
            "INNER JOIN despensa.categoria_producto c ON sc.id_categoria = c.id_categoria " +
            "WHERE p.stock_actual > 0 " +
            "ORDER BY p.nombre",
            nativeQuery = true)
    List<Producto> findProductosDisponiblesNative();

    /**
     * Productos más vendidos (requiere join con detalle_pedido)
     */
    @Query(value = "SELECT p.*, COALESCE(SUM(dp.cantidad), 0) as total_vendido " +
            "FROM despensa.producto p " +
            "LEFT JOIN despensa.detalle_pedido dp ON p.id_producto = dp.id_producto " +
            "GROUP BY p.id_producto " +
            "ORDER BY total_vendido DESC " +
            "LIMIT :limite",
            nativeQuery = true)
    List<Producto> findProductosMasVendidos(@Param("limite") int limite);

    // ==================== QUERIES NUEVAS PARA PROVEEDORES ====================

    /**
     * Obtener productos con el proveedor más barato
     */
    @Query("SELECT p FROM Producto p " +
            "WHERE p.idProducto IN (" +
            "  SELECT pp.producto.idProducto FROM ProductoProveedor pp " +
            "  WHERE pp.activo = true " +
            "  AND pp.precioCosto = (" +
            "    SELECT MIN(pp2.precioCosto) FROM ProductoProveedor pp2 " +
            "    WHERE pp2.producto.idProducto = pp.producto.idProducto " +
            "    AND pp2.activo = true" +
            "  )" +
            ")")
    List<Producto> findProductosConProveedorMasBarato();

    /**
     * Productos de una categoría con sus proveedores ordenados por precio
     */
    @Query("SELECT DISTINCT p FROM Producto p " +
            "JOIN p.subcategoria s " +
            "LEFT JOIN FETCH p.proveedores pp " +
            "LEFT JOIN FETCH pp.proveedor " +
            "WHERE s.categoria.idCategoria = :idCategoria " +
            "AND (pp.activo = true OR pp IS NULL) " +
            "ORDER BY pp.precioCosto ASC")
    List<Producto> findByCategoriaWithProveedoresOrderByPrecio(@Param("idCategoria") Long idCategoria);
}