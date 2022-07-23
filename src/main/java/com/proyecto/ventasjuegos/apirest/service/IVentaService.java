package com.proyecto.ventasjuegos.apirest.service;

import java.util.List;
import java.util.Optional;


import com.proyecto.ventasjuegos.apirest.entity.Venta;

public interface IVentaService {
	public List<Venta> finAll();
    public Optional<Venta> findById(Long id);

    public Venta save(Venta venta);

    public Venta update(Venta venta, Long id);

    public void delete(Long id);
}
