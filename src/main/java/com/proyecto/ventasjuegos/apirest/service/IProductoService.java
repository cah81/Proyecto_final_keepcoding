package com.proyecto.ventasjuegos.apirest.service;


import com.proyecto.ventasjuegos.apirest.entity.Producto;

import java.util.List;


public interface IProductoService {

    public List<Producto> finAll();
    //public Optional<Producto> findById(Long id);
    public Producto findById(Long id);
    public Producto save(Producto producto);

    public Producto update(Producto producto, Long id);

    public void delete(Long id);
}
