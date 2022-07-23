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

	

	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
