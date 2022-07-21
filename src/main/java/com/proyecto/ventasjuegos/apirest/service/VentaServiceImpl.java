package com.proyecto.ventasjuegos.apirest.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import com.proyecto.ventasjuegos.apirest.entity.Producto;
import com.proyecto.ventasjuegos.apirest.entity.Venta;
import com.proyecto.ventasjuegos.apirest.repository.VentaDao;


@Service
public class VentaServiceImpl implements VentaService {
	
	@Autowired
	private VentaDao ventaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> mostrarTodos() {
		// TODO Auto-generated method stub
		return (List<Venta>) ventaDao.findAll();
	}

	@Override
	@Transactional
	public Venta mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return ventaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta guardar(Venta venta) {
		// TODO Auto-generated method stub
		return ventaDao.save(venta);
	}

	@Override
	public void borrar(Long id) {
		ventaDao.deleteById(id);
		
	}

	@Override
	public List<Cliente> mostrarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> mostrarProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	@Transactional(readOnly = true)
	public List<Producto> mostrarProductos() {
		// TODO Auto-generated method stub
		return ventaDao.mostrarProductos();
	}*/

	/*@Override
	public List<Cliente> mostrarClientes() {
		// TODO Auto-generated method stub
		return ventaDao.mostrarClientes();
	}*/

	/*@Override
	public List<Producto> mostrarProductos() {
		// TODO Auto-generated method stub
		return ventaDao.mostrarTodosProductos();
	}*/

	

	

	/*@Override
	@Transactional(readOnly = true)
	public List<Cliente> mostrarClientes() {
	 //TODO Auto-generated method stub
		return ventaDao.mostrarClientes();
	}
*/
	

}
