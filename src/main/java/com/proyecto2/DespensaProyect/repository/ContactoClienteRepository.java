package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.domain.entity.ContactoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoClienteRepository extends JpaRepository<ContactoCliente, Long> {

    List<ContactoCliente> findByCliente(Cliente cliente);

    List<ContactoCliente> findByClienteIdCliente(Long idCliente);

    List<ContactoCliente> findByEmail(String email);

    List<ContactoCliente> findByTelefono(String telefono);
}