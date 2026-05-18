package com.proyecto2.DespensaProyect.service;


import com.proyecto2.DespensaProyect.domain.entity.TipoMovimiento;
import com.proyecto2.DespensaProyect.mapper.TipoMovimientoMapper;
import com.proyecto2.DespensaProyect.model.request.TipoMovimientoRequest;
import com.proyecto2.DespensaProyect.model.response.TipoMovimientoResponse;
import com.proyecto2.DespensaProyect.repository.TipoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoMovimientoService {

    @Autowired
    private TipoMovimientoRepository repository;
    @Autowired
    private TipoMovimientoMapper mapper;

    public void createTipoMovimiento(TipoMovimientoRequest request){
        TipoMovimiento newTipoMovimiento = request.toEntity();
        repository.save(newTipoMovimiento);
    }

    @Transactional(readOnly = true)
    public List<TipoMovimientoResponse> getMovementsTypes(){
        List<TipoMovimiento> entities = repository.findAll();
        return mapper.toResponseList(entities);
    }

    @Transactional(readOnly = true)
    public TipoMovimientoResponse getMovementsTypesById(Long id){
        return  repository.findById(id).map(mapper::toResponse).orElseThrow(() -> new RuntimeException("Tipo de movimiento de inventario no encontrado"));
    }

    public TipoMovimientoResponse patchMovementType(TipoMovimientoRequest request, Long id){
        TipoMovimiento tipoMovimiento = repository.findById(id).orElseThrow(() -> new RuntimeException("Tipo de movimiento no encontrado"));
        if(request.getName() != null){
            tipoMovimiento.setNombre(request.getName());
        }
        if(request.getDescription() != null ){
            tipoMovimiento.setDescription(request.getDescription());
        }
        return mapper.toResponse(repository.save(tipoMovimiento));
    }




}
