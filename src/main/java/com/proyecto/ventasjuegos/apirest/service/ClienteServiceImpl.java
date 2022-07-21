package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.dao.ClienteDao;
import com.proyecto.ventasjuegos.apirest.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> finAll() {
        List<Cliente> clientes = clienteDao.findAll();
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
