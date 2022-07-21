package com.proyecto.ventasjuegos.apirest.repository;

import com.proyecto.ventasjuegos.apirest.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
