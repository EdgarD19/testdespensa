package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.response.MaestroItemResponse;
import com.proyecto2.DespensaProyect.service.MaestrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Maestros para el frontend (reemplaza VITE_MAESTROS_JSON).
 */
@RestController
@RequestMapping("api/v1")
public class MaestrosController {

    @Autowired
    private MaestrosService maestrosService;

    @GetMapping(value = "/unidades-medida", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getUnidades() {
        return ResponseEntity.ok(maestrosService.listUnidades());
    }

    @GetMapping(value = "/proveedores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getProveedores() {
        return ResponseEntity.ok(maestrosService.listProveedores());
    }

    @GetMapping(value = "/marcas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getMarcas() {
        return ResponseEntity.ok(maestrosService.listMarcas());
    }

    @GetMapping(value = "/categories/{id}/subcategorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getSubcategorias(@PathVariable("id") Long idCategoria) {
        return ResponseEntity.ok(maestrosService.listSubcategorias(idCategoria));
    }

    @GetMapping(value = "/rubros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getRubros() {
        return ResponseEntity.ok(maestrosService.listRubros());
    }

    @GetMapping(value = "/paises", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getPaises() {
        return ResponseEntity.ok(maestrosService.listPaises());
    }

    @GetMapping(value = "/paises/{id}/ciudades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaestroItemResponse>> getCiudades(@PathVariable("id") Long idPais) {
        return ResponseEntity.ok(maestrosService.listCiudades(idPais));
    }
}
