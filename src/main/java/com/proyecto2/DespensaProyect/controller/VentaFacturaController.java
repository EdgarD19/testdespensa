package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.request.VentaFacturaRequest;
import com.proyecto2.DespensaProyect.model.response.VentaFacturaResponse;
import com.proyecto2.DespensaProyect.service.VentaFacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ventas")
public class VentaFacturaController {

    @Autowired
    private VentaFacturaService ventaFacturaService;

    @PostMapping(value = "/facturas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VentaFacturaResponse> registrar(@Valid @RequestBody VentaFacturaRequest request) {
        VentaFacturaResponse body = ventaFacturaService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
