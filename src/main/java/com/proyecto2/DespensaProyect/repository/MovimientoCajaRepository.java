package com.proyecto2.DespensaProyect.repository;


import com.proyecto2.DespensaProyect.domain.entity.Caja;
import com.proyecto2.DespensaProyect.domain.entity.MovimientoCaja;
import com.proyecto2.DespensaProyect.domain.entity.TipoMovimientoCaja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoCajaRepository extends JpaRepository<MovimientoCaja, Long> {

    List<MovimientoCaja> findByCaja(Caja caja);

    List<MovimientoCaja> findByCajaIdCaja(Long idCaja);

    Page<MovimientoCaja> findByCajaIdCajaOrderByFechaDesc(Long idCaja, Pageable pageable);

    List<MovimientoCaja> findByTipoMovimientoCaja(TipoMovimientoCaja tipo);

    List<MovimientoCaja> findByTipoMovimientoCajaIdTipoMovCaja(Long idTipo);

    List<MovimientoCaja> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT SUM(m.monto) FROM MovimientoCaja m " +
            "WHERE m.caja.idCaja = :idCaja " +
            "AND m.tipoMovimientoCaja.nombre = :tipo")
    BigDecimal calcularTotalPorTipo(
            @Param("idCaja") Long idCaja,
            @Param("tipo") String tipo);

    @Query("SELECT m FROM MovimientoCaja m " +
            "LEFT JOIN FETCH m.tipoMovimientoCaja " +
            "WHERE m.caja.idCaja = :idCaja " +
            "ORDER BY m.fecha DESC")
    List<MovimientoCaja> findByCajaWithTipo(@Param("idCaja") Long idCaja);
}