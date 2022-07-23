package com.proyecto.ventasjuegos.apirest.controller;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import com.proyecto.ventasjuegos.apirest.service.impl.ProveedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProveedorRestController {

    @Autowired
    private ProveedorServiceImpl proveedorService;

    @GetMapping({"/productos", "/"})
    public List<Proveedor> index() {
        return proveedorService.finAll();
    }
}
