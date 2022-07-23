package com.proyecto.ventasjuegos.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto.ventasjuegos.apirest.service.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ventasjuegos.apirest.entity.Cliente;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    private ClienteServiceImpl clienteService;
//================MOSTRAR LISTA DE CLIENTES==============================
    @GetMapping({"/clientes"})
    public List<Cliente> index() {
        return clienteService.finAll();
    }

//===============MOSTRAR CLIENTES POR ID=================================
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
        //Optional<Cliente> clienteBD = null;
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            //clienteBD = clienteService.findById(id);
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //clienteBD.isEmpty()
        if (cliente == null) {
            response.put("mensaje", "El producto con ID: " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
       // return new ResponseEntity<Cliente>(clienteBD.get(), HttpStatus.OK);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
//==============PARA CREAR NUEVO CLIENTE ================================
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            clienteNew = clienteService.save(cliente);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se ha creado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
//==================PARA ACTUALIZAR CLIENTE ===============================
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente
    	, @PathVariable Long id) {
    	
    	Cliente clienteUpdate = clienteService.findById(id);
        //Optional<Cliente> clienteBD = clienteService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (clienteUpdate == null) {
            response.put("mensaje", "no existe el registro con id: " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
       // Cliente clienteUpdate = clienteBD.get();
        try {
            clienteUpdate.setNombre(cliente.getNombre());
            clienteUpdate.setDni(cliente.getDni());
            clienteUpdate.setTelefono(cliente.getTelefono());
            clienteUpdate.setEmail(cliente.getEmail());
            clienteService.save(clienteUpdate);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error con la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se actualizo correctamente");
        response.put("cliente", clienteUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
 //========================PARA BORRAR CLIENTE ===================================

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
       // Optional<Cliente> clienteBD = clienteService.findById(id);
    	Cliente clienteBorrado= clienteService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (clienteBorrado == null) {
            response.put("mensaje", "No existe registro con id : " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        //Cliente clienteBorrado = clienteBD.get();
        try {
            clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "error con la base de datos");
            response.put("cliente", clienteBorrado);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("mensaje", "El cliente ha sido eliminado con éxito");
        response.put("cliente", clienteBorrado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
