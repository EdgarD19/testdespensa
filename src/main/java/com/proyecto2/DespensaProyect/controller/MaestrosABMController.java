package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.request.NombreRequest;
import com.proyecto2.DespensaProyect.service.MaestrosABMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/admin/maestros")
public class MaestrosABMController {

    @Autowired
    private MaestrosABMService service;

    // === CATEGORIAS ===

    @PostMapping(value = "/categorias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> crearCategoria(@RequestBody NombreRequest req) {
        var c = service.crearCategoria(req.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", c.getIdCategoria(), "nombre", c.getNombre()));
    }

    @PutMapping(value = "/categorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizarCategoria(@PathVariable Long id, @RequestBody NombreRequest req) {
        var c = service.actualizarCategoria(id, req.getNombre());
        return ResponseEntity.ok(Map.of("id", c.getIdCategoria(), "nombre", c.getNombre()));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        service.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // === SUBCATEGORIAS ===

    @PostMapping(value = "/categorias/{idCategoria}/subcategorias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> crearSubcategoria(@PathVariable Long idCategoria, @RequestBody NombreRequest req) {
        var s = service.crearSubcategoria(idCategoria, req.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", s.getIdSubcategoria(), "nombre", s.getNombre(), "idCategoria", idCategoria));
    }

    @PutMapping(value = "/subcategorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizarSubcategoria(@PathVariable Long id, @RequestBody NombreRequest req) {
        var s = service.actualizarSubcategoria(id, req.getNombre());
        return ResponseEntity.ok(Map.of("id", s.getIdSubcategoria(), "nombre", s.getNombre()));
    }

    @DeleteMapping("/subcategorias/{id}")
    public ResponseEntity<Void> eliminarSubcategoria(@PathVariable Long id) {
        service.eliminarSubcategoria(id);
        return ResponseEntity.noContent().build();
    }

    // === MARCAS ===

    @PostMapping(value = "/marcas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> crearMarca(@RequestBody NombreRequest req) {
        var m = service.crearMarca(req.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", m.getIdMarca(), "nombre", m.getNombre()));
    }

    @PutMapping(value = "/marcas/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizarMarca(@PathVariable Long id, @RequestBody NombreRequest req) {
        var m = service.actualizarMarca(id, req.getNombre());
        return ResponseEntity.ok(Map.of("id", m.getIdMarca(), "nombre", m.getNombre()));
    }

    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        service.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }

    // === PAISES ===

    @PostMapping(value = "/paises", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> crearPais(@RequestBody NombreRequest req) {
        var p = service.crearPais(req.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", p.getIdPais(), "nombre", p.getNombre()));
    }

    @PutMapping(value = "/paises/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizarPais(@PathVariable Long id, @RequestBody NombreRequest req) {
        var p = service.actualizarPais(id, req.getNombre());
        return ResponseEntity.ok(Map.of("id", p.getIdPais(), "nombre", p.getNombre()));
    }

    @DeleteMapping("/paises/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable Long id) {
        service.eliminarPais(id);
        return ResponseEntity.noContent().build();
    }

    // === CIUDADES ===

    @PostMapping(value = "/paises/{idPais}/ciudades", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> crearCiudad(@PathVariable Long idPais, @RequestBody NombreRequest req) {
        var c = service.crearCiudad(idPais, req.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", c.getIdCiudad(), "nombre", c.getNombre(), "idPais", idPais));
    }

    @PutMapping(value = "/ciudades/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizarCiudad(@PathVariable Long id, @RequestBody NombreRequest req) {
        var c = service.actualizarCiudad(id, req.getNombre());
        return ResponseEntity.ok(Map.of("id", c.getIdCiudad(), "nombre", c.getNombre()));
    }

    @DeleteMapping("/ciudades/{id}")
    public ResponseEntity<Void> eliminarCiudad(@PathVariable Long id) {
        service.eliminarCiudad(id);
        return ResponseEntity.noContent().build();
    }
}
