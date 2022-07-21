package com.proyecto.ventasjuegos.apirest.entity;

import java.io.Serializable;

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

@Entity
@Table(name = "detalleventa")
@Data
public class DetalleVenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precioVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Venta")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
