package com.sanjuan.pos.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class CompraDTO {
    private String factura;
    private String rfc;
    private LocalDate fecha;
    private List<DetalleCompraDTO> detalles;
}