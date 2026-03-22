package com.sanjuan.pos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetalleResponseDTO {

    private String producto;
    private String lote;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}