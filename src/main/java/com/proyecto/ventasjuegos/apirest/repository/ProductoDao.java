package com.proyecto.ventasjuegos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.ventasjuegos.apirest.entity.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long>{

}
