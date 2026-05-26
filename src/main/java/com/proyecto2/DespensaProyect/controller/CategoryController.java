package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.request.CategoryRequest;
import com.proyecto2.DespensaProyect.model.response.CategoryResponse;
import com.proyecto2.DespensaProyect.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

    @Autowired
    private CategoryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponse>> getCategorias() {
        return ResponseEntity.ok(service.listCategories());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody CategoryRequest request) {
      service.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




}
