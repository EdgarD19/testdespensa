package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.request.ProveedorRequest;
import com.proyecto2.DespensaProyect.model.response.ProveedoresResponse;
import com.proyecto2.DespensaProyect.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorRequest request) {
        proveedorService.createProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedoresResponse> getAllProveedores(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        ProveedoresResponse response = proveedorService.getProveedores(search, page, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProveedorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proveedorService.getProveedorById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProveedor(
            @PathVariable("id") Long id,
            @RequestBody ProveedorRequest request) {
        return ResponseEntity.ok(proveedorService.updateProveedor(id, request));
    }

    @PatchMapping(value = "/{id}/toggle-activo")
    public ResponseEntity<?> toggleActivo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proveedorService.toggleActivo(id));
    }
}
