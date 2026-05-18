package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.DetalleOrdenCompra;
import com.proyecto2.DespensaProyect.domain.entity.OrdenCompra;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, Long> {

    List<DetalleOrdenCompra> findByOrdenCompra(OrdenCompra ordenCompra);

    List<DetalleOrdenCompra> findByOrdenCompraIdOrdenCompra(Long idOrdenCompra);

    List<DetalleOrdenCompra> findByProducto(Producto producto);

    List<DetalleOrdenCompra> findByProducto(Long idProducto);

    @Query("SELECT d FROM DetalleOrdenCompra d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE d.ordenCompra.idOrdenCompra = :idOrdenCompra")
    List<DetalleOrdenCompra> findByOrdenCompraWithProductos(@Param("idOrdenCompra") Long idOrdenCompra);
}