package com.proyecto.ventasjuegos.apirest.repository;

import com.proyecto.ventasjuegos.apirest.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
