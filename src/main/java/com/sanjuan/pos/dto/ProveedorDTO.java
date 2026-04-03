package com.sanjuan.pos.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorDTO {
	private String RFC;
	private String nombre;
	private BigDecimal importe;
}
