package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.request.InventarioAjusteRequest;
import com.proyecto2.DespensaProyect.model.response.InventarioAjusteResponse;
import com.proyecto2.DespensaProyect.service.InventarioAjusteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/inventario")
public class InventarioAjusteController {

    @Autowired
    private InventarioAjusteService inventarioAjusteService;

    @GetMapping(value = "/ajustes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> listarAjustes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "50") int pageSize) {
        return ResponseEntity.ok(inventarioAjusteService.listar(page, pageSize));
    }

    @PostMapping(value = "/ajustes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InventarioAjusteResponse> crearAjuste(@RequestBody InventarioAjusteRequest request) {
        InventarioAjusteResponse body = inventarioAjusteService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PatchMapping(value = "/ajustes/{id}/autorizar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InventarioAjusteResponse> autorizarAjuste(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventarioAjusteService.autorizar(id));
    }
}
