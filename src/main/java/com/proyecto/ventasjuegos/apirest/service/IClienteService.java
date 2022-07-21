package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> finAll();
    public Cliente findById(Long id);

    public Cliente save(Cliente cliente);

    public Cliente update(Cliente cliente, Long id);

    public void delete(Long id);
}
