package com.proyecto.ventasjuegos.apirest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.proyecto.ventasjuegos.apirest.entity.Venta;

import com.proyecto.ventasjuegos.apirest.repository.VentaRepository;
import com.proyecto.ventasjuegos.apirest.service.IVentaService;

public class VentaServiceImpl implements IVentaService{
	
	
	@Autowired
    private VentaRepository ventaRepository;

	@Override
    public List<Venta> finAll() {
        return ventaRepository.findAll();
    }

	@Override
	public Optional<Venta> findById(Long id) {
		// TODO Auto-generated method stub
		return ventaRepository.findById(id);
	}

	@Override
	public Venta save(Venta venta) {
		// TODO Auto-generated method stub
		return ventaRepository.save(venta);
	}

	/*@Override
	public Venta update(Venta venta, Long id) {
		Optional<Venta> productoDB = ventaRepository.findById(id);
        Venta ventaUpdate = null;
        if(!ventaDB.isEmpty()){
            ventaUpdate = productoDB.get();
            
            ventaUpdate.setImagen(venta.getImagen());
            ventaUpdate.setNombre(venta.getNombre());
            ventaUpdate.setDescripcion(venta.getDescripcion());
            ventaUpdate.setStock(venta.getStock());
            ventaRepository.save(ventaUpdate);
        }
        return productoUpdate;
	}
*/
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venta update(Venta venta, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
