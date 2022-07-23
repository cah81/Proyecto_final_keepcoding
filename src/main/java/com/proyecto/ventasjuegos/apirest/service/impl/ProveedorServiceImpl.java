package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.Producto;
import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import com.proyecto.ventasjuegos.apirest.repository.ProveedorRepository;
import com.proyecto.ventasjuegos.apirest.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> finAll() {
        List<Proveedor> proveedors = proveedorRepository.findAll();
        return proveedors;
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor update(Proveedor proveedor, Long id) {
        Optional<Proveedor> proveedorDB = proveedorRepository.findById(id);
        Proveedor proveedorUpdate = null;
        if(!proveedorDB.isEmpty()){
            proveedorUpdate = proveedorDB.get();
            proveedorUpdate.setNombre(proveedor.getNombre());
            proveedorUpdate.setNif(proveedor.getNif());
            proveedorUpdate.setDireccion(proveedor.getDireccion());
            proveedorUpdate.setEmail(proveedor.getEmail());
            proveedorUpdate.setTelefono(proveedor.getTelefono());
            proveedorRepository.save(proveedor);
        }
        return proveedorUpdate;
    }

    @Override
    public void delete(Long id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if(!proveedor.isEmpty()){
            proveedorRepository.delete(proveedor.get());
        }
    }
}
