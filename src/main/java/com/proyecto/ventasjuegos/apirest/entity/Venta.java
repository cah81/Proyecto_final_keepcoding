package com.proyecto.ventasjuegos.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import lombok.Data;

@Entity
@Table(name = "venta")
@Data
public class Venta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String nroFactura;
	@Column(nullable = false)
	private Date fechaVenta;
	@Column(nullable = false)
	private double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Cliente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Long id_Cliente;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
