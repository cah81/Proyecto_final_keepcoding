package com.proyecto.ventasjuegos.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	private int cantidad;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private int precio;
	@Column(nullable = false)
	private int fecha_registro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Proveedor")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Proveedor proveedor;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
