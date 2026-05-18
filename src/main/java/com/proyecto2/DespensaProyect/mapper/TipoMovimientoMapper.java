package com.proyecto2.DespensaProyect.mapper;

import com.proyecto2.DespensaProyect.domain.entity.TipoMovimiento;
import com.proyecto2.DespensaProyect.model.response.TipoMovimientoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoMovimientoMapper {


    public TipoMovimientoResponse toResponse(TipoMovimiento movimiento) {
        if (movimiento == null) {
            return null;
        }
        return TipoMovimientoResponse.builder()
                .idMovimiento(movimiento.getIdTipoMovimiento().intValue())
                .nombre(movimiento.getNombre())
                .descripcion(movimiento.getDescription())
                .build();
    }

    public List<TipoMovimientoResponse> toResponseList(List<TipoMovimiento> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .map(this::toResponse)
                .toList();
    }


}
