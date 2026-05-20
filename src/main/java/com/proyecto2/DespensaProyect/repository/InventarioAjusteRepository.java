package com.proyecto2.DespensaProyect.repository;

import com.proyecto2.DespensaProyect.domain.entity.InventarioAjuste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioAjusteRepository extends JpaRepository<InventarioAjuste, Long> {

    Page<InventarioAjuste> findAllByOrderByIdDesc(Pageable pageable);
}
