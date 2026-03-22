package com.sanjuan.pos.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VentaResponseDTO {

    private Long id;
    private LocalDateTime fecha;
    private BigDecimal total;
    private List<DetalleResponseDTO> detalles;
}