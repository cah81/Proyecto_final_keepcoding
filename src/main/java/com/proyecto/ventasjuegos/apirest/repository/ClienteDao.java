package com.proyecto.ventasjuegos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

}
