package com.sanjuan.pos.dto;

import lombok.Data;

@Data
public class DetalleVentaRequestDTO {

    private Long productoId;
    private Long loteId;
    private Integer cantidad;
}