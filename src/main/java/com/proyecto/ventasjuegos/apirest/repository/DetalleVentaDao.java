package com.proyecto.ventasjuegos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;

public interface DetalleVentaDao  extends JpaRepository<DetalleVenta, Long>{

}
