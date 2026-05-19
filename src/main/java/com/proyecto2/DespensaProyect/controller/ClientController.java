package com.proyecto2.DespensaProyect.controller;


import com.proyecto2.DespensaProyect.model.request.ClientRequest;
import com.proyecto2.DespensaProyect.model.response.ClientsResponse;
import com.proyecto2.DespensaProyect.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ClientService clientService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(
            @RequestBody(required = true)  ClientRequest request){
        clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientsResponse> getAllClients(
            @RequestParam(value= "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir){
        ClientsResponse response = clientService.getClients(search, page, pageSize, sortBy,sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClientById(
            @PathVariable(value = "id") Long id
    ){
            return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(
            @PathVariable("id") Long id,
            @RequestBody(required = true) ClientRequest request) {
        return ResponseEntity.ok(clientService.updateClient(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
