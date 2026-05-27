package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {

    Optional<CategoriaProducto> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<CategoriaProducto> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT c FROM CategoriaProducto c ORDER BY c.nombre ASC")
    List<CategoriaProducto> findAllOrderByNombre();

    @Query("SELECT COUNT(p) FROM Producto p JOIN p.subcategoria s WHERE s.categoria.idCategoria = :id")
    long countProductosByCategoria(@Param("id") Long idCategoria);
}