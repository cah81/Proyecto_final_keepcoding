package com.proyecto.ventasjuegos.apirest.service;

import java.util.List;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import com.proyecto.ventasjuegos.apirest.entity.Producto;
import com.proyecto.ventasjuegos.apirest.entity.Venta;

public interface VentaService {

	public List<Venta> mostrarTodos();
	public Venta mostrarPorId(Long id);
	public Venta guardar(Venta venta);
	public void borrar(Long id);
	
	//public List<Producto> mostrarProductos();
	public List<Cliente> mostrarClientes();
	
	public List<Producto> mostrarProductos();
	
}
