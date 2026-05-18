package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.SubcategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoriaProductoRepository extends JpaRepository<SubcategoriaProducto, Long> {

    List<SubcategoriaProducto> findByCategoria(CategoriaProducto categoria);

    List<SubcategoriaProducto> findByCategoriaIdCategoria(Long idCategoria);

    Optional<SubcategoriaProducto> findByNombreAndCategoriaIdCategoria(String nombre, Long idCategoria);

    boolean existsByNombreAndCategoriaIdCategoria(String nombre, Long idCategoria);

    @Query("SELECT s FROM SubcategoriaProducto s " +
            "LEFT JOIN FETCH s.categoria " +
            "WHERE s.idSubcategoria = :id")
    Optional<SubcategoriaProducto> findByIdWithCategoria(@Param("id") Long id);
}
