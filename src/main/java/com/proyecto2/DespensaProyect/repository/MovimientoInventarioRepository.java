package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.MovimientoInventario;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.domain.entity.TipoMovimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    List<MovimientoInventario> findByProducto(Producto producto);

    List<MovimientoInventario> findByProducto(Long idProducto);

    List<MovimientoInventario> findByTipoMovimiento(TipoMovimiento tipoMovimiento);

    List<MovimientoInventario> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<MovimientoInventario> findByProductoOrderByFechaDesc(Long idProducto, Pageable pageable);

    @Query("SELECT m FROM MovimientoInventario m " +
            "WHERE m.producto.idProducto = :idProducto " +
            "AND m.fecha BETWEEN :inicio AND :fin " +
            "ORDER BY m.fecha DESC")
    List<MovimientoInventario> findByProductoAndFecha(
            @Param("idProducto") Long idProducto,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT m FROM MovimientoInventario m " +
            "LEFT JOIN FETCH m.producto " +
            "LEFT JOIN FETCH m.tipoMovimiento " +
            "WHERE m.idMovimiento = :id")
    MovimientoInventario findByIdWithDetails(@Param("id") Long id);
}