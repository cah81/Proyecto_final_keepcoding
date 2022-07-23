package com.proyecto.ventasjuegos.apirest.repository;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    //Metodo para obtener un proveedor por su nombre
    public List<Proveedor> findByNombre(String nombre);
}
