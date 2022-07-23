package com.proyecto.ventasjuegos.apirest.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "cliente")
@Data
public class Cliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false, unique = true)
	private String dni;
	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private int telefono;
	@Column(name = "create_at")
	private Date createAt;
	private String imagen;

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
