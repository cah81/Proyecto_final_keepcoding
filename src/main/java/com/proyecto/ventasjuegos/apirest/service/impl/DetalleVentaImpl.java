package com.proyecto.ventasjuegos.apirest.service.impl;

import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;
import com.proyecto.ventasjuegos.apirest.repository.DetalleVentaRepository;
import com.proyecto.ventasjuegos.apirest.service.IDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaImpl implements IDetalleVentaService {
    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> finAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    //TODO: Revisar esto que no me acuerdo bien
    public DetalleVenta update(DetalleVenta detalleVenta, Long id) {
        Optional<DetalleVenta> detalleVentaOld = detalleVentaRepository.findById(id);
        if (detalleVentaOld.isEmpty()){
            return detalleVentaRepository.save(detalleVenta);
        } else {
            detalleVentaOld.orElseThrow().setVenta(detalleVenta.getVenta());
            detalleVentaOld.orElseThrow().setCantidad(detalleVenta.getCantidad());
            detalleVentaOld.orElseThrow().setPrecioVenta(detalleVenta.getPrecioVenta());
            detalleVentaOld.orElseThrow().setProducto(detalleVenta.getProducto());
            return detalleVentaRepository.save(detalleVentaOld.get());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<DetalleVenta> detalleVentaOld = detalleVentaRepository.findById(id);
        detalleVentaRepository.delete(detalleVentaOld.get());
    }
}
