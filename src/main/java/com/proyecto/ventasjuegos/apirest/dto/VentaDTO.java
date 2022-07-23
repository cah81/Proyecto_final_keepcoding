package com.proyecto.ventasjuegos.apirest.dto;
import java.sql.Date;
import java.util.List;

import lombok.Data;
//Create DTO for Venta
@Data
public class VentaDTO {

    private Date fechaVenta;
    private double monto;
    private Long idCliente;

    public List<ListaProductosDTO> listaProductosDTOs;
}

