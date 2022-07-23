package com.proyecto.ventasjuegos.apirest.entity;



import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "producto")
@Data

public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false, unique = true)
	private String codigo_producto;
	@Column(nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private String imagen;
	@Column(nullable = false)
	private int stock;

	@Column(nullable = false)
	private double precio;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
