package com.sanjuan.pos.dto;

import lombok.Data;

@Data
public class InventarioLoteRequestDTO {

    private Long productoId;
    private String lote;
    private Integer cantidad;
    private String fechaCaducidad;
}