package com.proyecto2.DespensaProyect.controller;


import com.proyecto2.DespensaProyect.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/inventario")
public class InventarioController {

    private static final Logger LOGGER = Logger.getLogger(InventarioController.class.getName());

    @Autowired
    private InventarioService inventarioService;
}
