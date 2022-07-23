package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import com.proyecto.ventasjuegos.apirest.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor update(Proveedor proveedor, Long id) {
        return proveedorRepository.save(proveedor);
    }

    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }

    public List<Proveedor> findByNombre(String nombre) {
        return proveedorRepository.findByNombre(nombre);
    }
}
