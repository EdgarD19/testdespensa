package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.DetalleFacturaCompra;
import com.proyecto2.DespensaProyect.domain.entity.FacturaCompra;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaCompraRepository extends JpaRepository<DetalleFacturaCompra, Long> {

    List<DetalleFacturaCompra> findByFacturaCompra(FacturaCompra facturaCompra);

    List<DetalleFacturaCompra> findByFacturaCompraIdFacturaCompra(Long idFacturaCompra);

    List<DetalleFacturaCompra> findByProducto(Producto producto);

    List<DetalleFacturaCompra> findByProducto(Long idProducto);

    @Query("SELECT d FROM DetalleFacturaCompra d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE d.facturaCompra.idFacturaCompra = :idFacturaCompra")
    List<DetalleFacturaCompra> findByFacturaCompraWithProductos(@Param("idFacturaCompra") Long idFacturaCompra);
}

