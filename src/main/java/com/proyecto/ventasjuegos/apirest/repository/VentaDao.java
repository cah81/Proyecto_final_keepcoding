package com.proyecto.ventasjuegos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.ventasjuegos.apirest.entity.Venta;

public interface VentaDao extends JpaRepository<Venta, Long> {

}
