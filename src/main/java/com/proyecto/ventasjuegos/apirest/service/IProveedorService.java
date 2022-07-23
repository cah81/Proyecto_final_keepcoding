package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    public List<Proveedor> finAll();
    public Optional<Proveedor> findById(Long id);

    public Proveedor save(Proveedor proveedor);

    public Proveedor update(Proveedor proveedor, Long id);

    public void delete(Long id);
}
