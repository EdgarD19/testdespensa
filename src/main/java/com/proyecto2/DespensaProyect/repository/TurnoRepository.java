package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Empleado;
import com.proyecto2.DespensaProyect.domain.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findByEmpleado(Empleado empleado);

    List<Turno> findByEmpleadoIdEmpleado(Long idEmpleado);

    List<Turno> findByDescripcionContainingIgnoreCase(String descripcion);

    @Query("SELECT t FROM Turno t WHERE t.horaInicio >= :inicio AND t.horaFin <= :fin")
    List<Turno> findByRangoHorario(@Param("inicio") LocalTime inicio, @Param("fin") LocalTime fin);

    @Query("SELECT t FROM Turno t " +
            "LEFT JOIN FETCH t.empleado " +
            "WHERE t.idTurno = :id")
    Turno findByIdWithEmpleado(@Param("id") Long id);
}
