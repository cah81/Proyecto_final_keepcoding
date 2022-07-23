package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.Producto;
import com.proyecto.ventasjuegos.apirest.repository.ProductoRepository;
import com.proyecto.ventasjuegos.apirest.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> finAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        Optional<Producto> productoDB = productoRepository.findById(id);
        Producto productoUpdate = null;
        if(!productoDB.isEmpty()){
            productoUpdate = productoDB.get();
            productoUpdate.setCodigo_producto(producto.getCodigo_producto());
            productoUpdate.setImagen(producto.getImagen());
            productoUpdate.setNombre(producto.getNombre());
            productoUpdate.setDescripcion(producto.getDescripcion());
            productoUpdate.setStock(producto.getStock());
            productoRepository.save(productoUpdate);
        }
        return productoUpdate;
    }

    @Override
    public void delete(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(!producto.isEmpty()){
            productoRepository.delete(producto.get());
        }
    }
}
