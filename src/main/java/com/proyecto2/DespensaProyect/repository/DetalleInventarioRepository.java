package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.DetalleInventario;
import com.proyecto2.DespensaProyect.domain.entity.Inventario;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleInventarioRepository extends JpaRepository<DetalleInventario, Long> {

    List<DetalleInventario> findByInventario(Inventario inventario);

    List<DetalleInventario> findByInventarioIdInventario(Long idInventario);

    List<DetalleInventario> findByProducto(Producto producto);

    List<DetalleInventario> findByProducto(Long idProducto);

    @Query("SELECT d FROM DetalleInventario d WHERE d.diferencia <> 0")
    List<DetalleInventario> findConDiferencias();

    @Query("SELECT d FROM DetalleInventario d " +
            "WHERE d.inventario.idInventario = :idInventario " +
            "AND d.diferencia <> 0")
    List<DetalleInventario> findDiferenciasByInventario(@Param("idInventario") Long idInventario);
}