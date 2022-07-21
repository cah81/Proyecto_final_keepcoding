package com.proyecto.ventasjuegos.apirest.service;

import com.proyecto.ventasjuegos.apirest.dao.DetalleVentaDao;
import com.proyecto.ventasjuegos.apirest.entity.DetalleVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaImpl implements IDetalleVentaService {
    @Autowired
    DetalleVentaDao detalleVentaDao;

    @Override
    public List<DetalleVenta> finAll() {
        return detalleVentaDao.findAll();
    }

    @Override
    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaDao.findById(id.toString());
    }

    @Override
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaDao.save(detalleVenta);
    }

    @Override
    //TODO: Revisar esto que no me acuerdo bien
    public DetalleVenta update(DetalleVenta detalleVenta, Long id) {
        Optional<DetalleVenta> detalleVentaOld = detalleVentaDao.findById(id.toString());
        if (detalleVentaOld.isEmpty()){
            return detalleVentaDao.save(detalleVenta);
        } else {
            detalleVentaOld.orElseThrow().setVenta(detalleVenta.getVenta());
            detalleVentaOld.orElseThrow().setCantidad(detalleVenta.getCantidad());
            detalleVentaOld.orElseThrow().setPrecioVenta(detalleVenta.getPrecioVenta());
            detalleVentaOld.orElseThrow().setProducto(detalleVenta.getProducto());
            return detalleVentaDao.save(detalleVentaOld.get());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<DetalleVenta> detalleVentaOld = detalleVentaDao.findById(id.toString());
        detalleVentaDao.delete(detalleVentaOld.get());
    }
}
