package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.TransferenciaInformacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaInformacionRepository extends JpaRepository<TransferenciaInformacion, Long> {

    List<TransferenciaInformacion> findByOrigen(String origen);

    List<TransferenciaInformacion> findByDestino(String destino);

    List<TransferenciaInformacion> findByOrigenAndDestino(String origen, String destino);

    List<TransferenciaInformacion> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<TransferenciaInformacion> findByOrderByFechaDesc(Pageable pageable);
}
