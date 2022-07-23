package com.proyecto.ventasjuegos.apirest.repository;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
