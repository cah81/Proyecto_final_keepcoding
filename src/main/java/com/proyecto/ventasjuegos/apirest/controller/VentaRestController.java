package com.proyecto.ventasjuegos.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto.ventasjuegos.apirest.dto.ListaProductosDTO;
import com.proyecto.ventasjuegos.apirest.dto.VentaDTO;
import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;
import com.proyecto.ventasjuegos.apirest.entity.Producto;
import com.proyecto.ventasjuegos.apirest.entity.Venta;
import com.proyecto.ventasjuegos.apirest.service.IDetalleVentaService;
import com.proyecto.ventasjuegos.apirest.service.IVentaService;
import com.proyecto.ventasjuegos.apirest.service.impl.ClienteServiceImpl;
import com.proyecto.ventasjuegos.apirest.service.impl.DetalleVentaServiceImpl;
import com.proyecto.ventasjuegos.apirest.service.impl.ProductoServiceImpl;
import com.proyecto.ventasjuegos.apirest.service.impl.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/api")

public class VentaRestController {
	@Autowired
	private VentaServiceImpl ventaService;

	@Autowired
	private DetalleVentaServiceImpl detalleVentaService;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private ProductoServiceImpl productoService;

	@GetMapping({ "/ventas", "/" })
	public List<Venta> mostrarTodasLasVentas() {
		return ventaService.finAll();
	}

	@GetMapping("/venta/{id}")
	public Venta mostrarVenta(@PathVariable Long id) {
		//TODO control de errores
		return ventaService.findById(id).get();
	}

	@GetMapping("/ventas/cliente/{id}")
	public List<Venta> MostrarVentaPorCliente(@PathVariable Long id) {
		return ventaService.finAllById(id);
	}

	@PostMapping("/venta")
	public ResponseEntity<?> create(@Valid @RequestBody VentaDTO ventaDTO) {
		Map<String, Object> response = new HashMap<>();
		//Calcular monto total de la venta
		double montoTotal = 0;
		List<ListaProductosDTO> listaProductos = ventaDTO.getListaProductosDTOs();
		for (ListaProductosDTO listaProducto : listaProductos) {
			Producto producto = productoService.findById(listaProducto.getIdProducto());
			montoTotal += producto.getPrecio();
		}
		// Crear venta
		Venta venta = Venta.builder()
				.cliente(clienteService.findById(ventaDTO.getIdCliente()))
				.fechaVenta(ventaDTO.getFechaVenta())
				.monto(montoTotal)
				.build();
		try {
			venta = ventaService.save(venta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear la venta");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Por cada producto de la lista de detalle, crear detalle venta
		for (ListaProductosDTO listaProducto : listaProductos) {
			Producto producto = productoService.findById(listaProducto.getIdProducto());
			DetalleVenta detalleVenta = DetalleVenta.builder()
					.venta(venta)
					.producto(producto)
					.cantidad(listaProducto.getCantidad())
					.precioVenta(producto.getPrecio())
					.build();
			detalleVentaService.save(detalleVenta);
		}
		response.put("mensaje", "Venta creada con éxito");
		response.put("venta", venta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}








