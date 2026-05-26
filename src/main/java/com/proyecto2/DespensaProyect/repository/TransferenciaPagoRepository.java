package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.TransferenciaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaPagoRepository extends JpaRepository<TransferenciaPago, Long> {
    List<TransferenciaPago> findByFacturaIdFactura(Long idFactura);
}
