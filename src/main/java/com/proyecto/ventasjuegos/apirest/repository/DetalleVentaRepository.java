package com.proyecto.ventasjuegos.apirest.repository;

import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
