package com.proyecto2.DespensaProyect.controller;


import com.proyecto2.DespensaProyect.model.request.TipoMovimientoRequest;
import com.proyecto2.DespensaProyect.model.response.TipoMovimientoResponse;
import com.proyecto2.DespensaProyect.service.TipoMovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/tipo-movimientos-inventario")
public class TipoMovimientoController {

    private static final Logger LOGGER = Logger.getLogger(TipoMovimientoController.class.getName());


    @Autowired
    private TipoMovimientoService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoMovimientoResponse> createTipoMovimiento(
            @Valid @RequestBody(required = true) TipoMovimientoRequest request){
        service.createTipoMovimiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoMovimientoResponse>> getAllMovements(){
        List<TipoMovimientoResponse> response = service.getMovementsTypes();
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoMovimientoResponse> getMovementsById(
            @PathVariable(value = "id") Long id
    ){
                return ResponseEntity.ok(service.getMovementsTypesById(id));
    }


    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoMovimientoResponse> patchMovementType(
            @Valid @RequestBody(required = true) TipoMovimientoRequest request,
            @PathVariable(value = "id") Long id
    ){
        return ResponseEntity.ok(service.patchMovementType(request, id));

    }
}
