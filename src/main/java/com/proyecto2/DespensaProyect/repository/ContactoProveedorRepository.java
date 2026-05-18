package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.ContactoProveedor;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoProveedorRepository extends JpaRepository<ContactoProveedor, Long> {

    List<ContactoProveedor> findByProveedor(Proveedor proveedor);

    List<ContactoProveedor> findByProveedorIdProveedor(Long idProveedor);

    List<ContactoProveedor> findByEmail(String email);
}