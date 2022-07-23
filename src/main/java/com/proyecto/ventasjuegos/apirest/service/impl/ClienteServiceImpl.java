package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import com.proyecto.ventasjuegos.apirest.repository.ClienteRepository;
import com.proyecto.ventasjuegos.apirest.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> finAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente, Long id) { return null; }

    @Override
    public void delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(!cliente.isEmpty()){
            clienteRepository.delete(cliente.get());
        }
    }
}
