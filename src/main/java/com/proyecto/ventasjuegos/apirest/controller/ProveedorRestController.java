package com.proyecto.ventasjuegos.apirest.controller;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import com.proyecto.ventasjuegos.apirest.service.impl.ProveedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProveedorRestController {

    @Autowired
    private ProveedorServiceImpl proveedorService;

    //Endpoint para obtener todos los proveedores
    @GetMapping("/proveedores")
    public List<Proveedor> getAllProveedores() {
        return proveedorService.findAll();
    }

    //Endpoint para obtener un proveedor por su id
    @GetMapping("/proveedor/{id}")
    public Proveedor getProveedorById(@PathVariable Long id) {
        return proveedorService.findById(id).get();
    }

    //Endpoint para crear un proveedor
    @PostMapping("/proveedor")
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    //Endpoint para actualizar un proveedor
    @PutMapping("/proveedor/{id}")
    public Proveedor updateProveedor(@RequestBody Proveedor proveedor, @PathVariable Long id) {
        return proveedorService.update(proveedor, id);
    }

    //Endpoint para eliminar un proveedor
    @DeleteMapping("/proveedor/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorService.delete(id);
    }
}
