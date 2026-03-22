package com.sanjuan.pos.dto;

import lombok.Data;

import java.util.List;

@Data
public class VentaRequestDTO {

    private List<DetalleVentaRequestDTO> detalles;
}