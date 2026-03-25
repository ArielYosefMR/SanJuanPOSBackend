package com.sanjuan.pos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DetalleCompraDTO {
    private String codigo;
    private Integer cantidad;
    private BigDecimal costo;
    private String lote;
    private LocalDate caducidad;
}