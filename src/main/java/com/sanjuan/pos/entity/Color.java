package com.sanjuan.pos.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Color")
@Getter
@Setter
public class Color {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "color", nullable = false)
	private String color;
	
	@Column(name = "porcentaje", nullable = false)
	private BigDecimal porcentaje;	
}
