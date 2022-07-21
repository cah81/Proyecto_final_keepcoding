package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    public List<DetalleVenta> finAll();
    public Optional<DetalleVenta> findById(Long id);

    public DetalleVenta save(DetalleVenta detalleVenta);

    public DetalleVenta update(DetalleVenta detalleVenta, Long id);

    public void delete(Long id);
}
