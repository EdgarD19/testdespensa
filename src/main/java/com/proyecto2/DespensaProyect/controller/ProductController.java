package com.proyecto2.DespensaProyect.controller;

import com.proyecto2.DespensaProyect.model.detailResponse.ProductResponse;
import com.proyecto2.DespensaProyect.model.request.PatchRequest;
import com.proyecto2.DespensaProyect.model.request.ProductRequest;
import com.proyecto2.DespensaProyect.model.response.ProductsResponse;
import com.proyecto2.DespensaProyect.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ProductService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsResponse> getAllProducts(
            @RequestParam(value= "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir)
    {
        ProductsResponse response =  service.getProducts(search, page, pageSize, sortBy,sortDir);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/barcode/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getProductByBarcode(@PathVariable("codigo") String codigo) {
        return service.getProductByBarcode(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody(required = true)  ProductRequest request) {
         service.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProductsById(id));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> patchProductById(@PathVariable("id") Long id,
                                                            @RequestBody PatchRequest request) {
        return ResponseEntity.ok(service.patchProductById(id, request));

    }

    @PostMapping(value = "/deactivateProduct/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        service.deleteProductById(id);
    return ResponseEntity.noContent().build();
    }
}
