package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.Venta;
import com.proyecto.ventasjuegos.apirest.repository.VentaRepository;
import com.proyecto.ventasjuegos.apirest.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VentaServiceImpl implements IVentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> finAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta update(Venta producto, Long id) {
        Optional<Venta> ventaDB = VentaRepository.findById(id);
        Venta ventaUpdate = null;
        if(!ventaDB.isEmpty()){
            ventaUpdate = ventaDB.get();
            ventaUpdate.setNroFactura((ventaUpdate.getNroFactura());
            ventaUpdate.setFechaVenta(ventaUpdate.getFechaVenta());
            ventaUpdate.setMonto(ventaUpdate.getMonto());


            ventaRepository.save(ventaUpdate);
        }
        return ventaUpdate;
    }

    @Override
    public void delete(Long id) {

    }
    //El parametro del id es el id del cliente, no de la factura. Devuelve una lista de factura
    //filtrada por cliente
    public List<Venta> finAllById(Long id) {
        List<Venta> ventas= ventaRepository.findAll();
        return ventas.stream().filter(element -> element.getId_Cliente().equals(id)).collect(Collectors.toList());

    }
}
