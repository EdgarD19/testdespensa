package com.proyecto2.DespensaProyect.controller;


import com.proyecto2.DespensaProyect.model.response.MovimientosInventarioResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/movimientos-inventario")
public class MovimientoInventarioController {

    private static final Logger LOGGER = Logger.getLogger(MovimientoInventarioController.class.getName());


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovimientosInventarioResponse> getMovements(
            @RequestParam(value = "productId") Long productId,
            @RequestParam(value = "tipoMovimientoId") Long tipoMovimientoId,
            @RequestParam(value = "fechaInicio") Date fechaInicio,
            @RequestParam(value = "fechaFin") Date fechaFin,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ){
        throw new UnsupportedOperationException("Pendiente de implementación");
    }



//
//    @PostMapping(value = "/entrada", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public void entryProduct(){

   // }


}
