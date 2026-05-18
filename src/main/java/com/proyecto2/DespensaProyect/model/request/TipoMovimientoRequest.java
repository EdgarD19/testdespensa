package com.proyecto2.DespensaProyect.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoMovimientoRequest implements Serializable {

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("descripcion")
    private String description;

    public TipoMovimiento toEntity(){
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setNombre(this.getName());
        tipoMovimiento.setDescription(this.getDescription());
        return  tipoMovimiento;
    }

}
