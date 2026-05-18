package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Caja;
import com.proyecto2.DespensaProyect.domain.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {

    List<Caja> findByEmpleado(Empleado empleado);

    List<Caja> findByEmpleadoIdEmpleado(Long idEmpleado);

    List<Caja> findByEstado(String estado);

    Optional<Caja> findFirstByEstadoOrderByFechaAperturaDesc(String estado);

    @Query("SELECT c FROM Caja c WHERE c.estado = 'ABIERTA'")
    Optional<Caja> findCajaAbierta();

    List<Caja> findByFechaAperturaBetween(LocalDateTime inicio, LocalDateTime fin);

    Page<Caja> findByOrderByFechaAperturaDesc(Pageable pageable);

    @Query("SELECT c FROM Caja c " +
            "LEFT JOIN FETCH c.empleado " +
            "WHERE c.idCaja = :id")
    Optional<Caja> findByIdWithEmpleado(@Param("id") Long id);

    @Query("SELECT c FROM Caja c " +
            "LEFT JOIN FETCH c.movimientos " +
            "WHERE c.idCaja = :id")
    Optional<Caja> findByIdWithMovimientos(@Param("id") Long id);

    boolean existsByEstado(String estado);
}