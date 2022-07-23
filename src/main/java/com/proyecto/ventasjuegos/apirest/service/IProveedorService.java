package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;

import java.util.List;
import java.util.Optional;

//Interfaz para proveedor
public interface IProveedorService {

    //Metodo para obtener todos los proveedores
    public List<Proveedor> findAll();

    //Metodo para obtener un proveedor por su id
    public Proveedor findById(Long id);

    //Metodo para crear un proveedor
    public Proveedor save(Proveedor proveedor);

    //Metodo para actualizar un proveedor
    public Proveedor update(Proveedor proveedor, Long id);

    //Metodo para eliminar un proveedor
    public void delete(Long id);

    //Metodo para obtener todos los proveedores por su nombre
    public List<Proveedor> findByNombre(String nombre);

}