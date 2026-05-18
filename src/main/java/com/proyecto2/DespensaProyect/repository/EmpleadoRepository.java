package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.Empleado;
import com.proyecto2.DespensaProyect.domain.entity.RolEmpleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar por documento
    Optional<Empleado> findByDocumento(String documento);

    // Verificar si existe por documento
    boolean existsByDocumento(String documento);

    // Buscar por nombre o apellido (case-insensitive)
    List<Empleado> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
            String nombre, String apellido
    );

    // Buscar por rol
    List<Empleado> findByRol(RolEmpleado rol);

    // Buscar por rol ID
    List<Empleado> findByRolIdRol(Long idRol);

    // Buscar por ciudad
    @Query("SELECT e FROM Empleado e WHERE e.ciudad.idCiudad = :idCiudad")
    List<Empleado> findByCiudadId(@Param("idCiudad") Long idCiudad);

    // Obtener empleado con todas sus relaciones (para detalles completos)
    @Query("SELECT e FROM Empleado e " +
            "LEFT JOIN FETCH e.rol " +
            "LEFT JOIN FETCH e.ciudad " +
            "WHERE e.idEmpleado = :id")
    Optional<Empleado> findByIdWithDetails(@Param("id") Long id);

    // Paginación con búsqueda
    @Query("SELECT e FROM Empleado e WHERE " +
            "LOWER(e.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(e.apellido) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "e.documento LIKE CONCAT('%', :termino, '%')")
    Page<Empleado> buscarPorTermino(@Param("termino") String termino, Pageable pageable);

    // Contar empleados por rol
    long countByRolIdRol(Long idRol);
}
