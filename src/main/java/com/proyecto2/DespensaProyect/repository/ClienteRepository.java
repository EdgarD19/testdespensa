package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends  JpaRepository<Cliente, Long>,
                                            JpaSpecificationExecutor<Cliente> {

    Optional<Cliente> findByDocumentNumber(String documento);

    boolean existsByDocumentNumber(String documento);

    List<Cliente> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String nombre, String apellido);

    Page<Cliente> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String nombre, String apellido, Pageable pageable);

    List<Cliente> findByCiudadIdCiudad(Long idCiudad);

    @Query("SELECT c FROM Cliente c " +
            "LEFT JOIN FETCH c.ciudad " +
            "WHERE c.idCliente = :id")
    Optional<Cliente> findByIdWithCiudad(@Param("id") Long id);

    @Query("SELECT c FROM Cliente c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "c.documentNumber LIKE CONCAT('%', :termino, '%') OR " +
            "c.phone LIKE CONCAT('%', :termino, '%')")
    Page<Cliente> buscarPorTermino(@Param("termino") String termino, Pageable pageable);

    @Query("SELECT COUNT(f) FROM Factura f WHERE f.cliente.idCliente = :id")
    long countFacturasByCliente(@Param("id") Long idCliente);
}
