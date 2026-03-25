package com.sanjuan.pos.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class Proveedor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String RFC;
	
	@Column(nullable = false, unique = true)
    private String nombre;
	
	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;
	
	@Column(nullable = false, precision = 8, scale = 6)
	private BigDecimal porcentaje;
}
