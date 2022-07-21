package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import com.proyecto.ventasjuegos.apirest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> finAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Cliente findById(Long id) {
        return null;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente update(Cliente cliente, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
