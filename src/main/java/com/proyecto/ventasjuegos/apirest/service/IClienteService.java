package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public List<Cliente> finAll();
    public Optional<Cliente> findById(Long id);

    public Cliente save(Cliente cliente);

    public Cliente update(Cliente cliente, Long id);

    public void delete(Long id);
}
