package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.ProductoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, Long> {

    List<ProductoProveedor> findByProductoIdProducto(Long idProducto);

    List<ProductoProveedor> findByProveedorIdProveedor(Long idProveedor);

    List<ProductoProveedor> findByProductoIdProductoAndActivoTrue(Long idProducto);

    Optional<ProductoProveedor> findByProductoIdProductoAndProveedorIdProveedor(
            Long idProducto, Long idProveedor);

    @Query("SELECT pp FROM ProductoProveedor pp " +
            "WHERE pp.producto.idProducto = :idProducto " +
            "ORDER BY pp.precioCosto ASC")
    List<ProductoProveedor> findProveedoresOrdenadosPorPrecio(@Param("idProducto") Long idProducto);

    @Query("SELECT pp FROM ProductoProveedor pp " +
            "WHERE pp.producto.idProducto = :idProducto " +
            "AND pp.activo = true " +
            "ORDER BY pp.precioCosto ASC " +
            "LIMIT 1")
    Optional<ProductoProveedor> findProveedorMasBarato(@Param("idProducto") Long idProducto);
}