package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Ciudad;
import com.proyecto2.DespensaProyect.domain.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    List<Ciudad> findByPais(Pais pais);

    List<Ciudad> findByPaisIdPais(Long idPais);

    Optional<Ciudad> findByNombreAndPais(String nombre, Pais pais);

    @Query("SELECT c FROM Ciudad c JOIN FETCH c.pais WHERE c.idCiudad = :id")
    Optional<Ciudad> findByIdWithPais(@Param("id") Long id);

    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);
}
