package com.sanjuan.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificacion
    @Column(nullable = false, unique = true)
    private String clave;

    @Column(name = "codigo_barras", unique = true)
    private String codigoBarras;
    
    @Column(name = "codigo_barras_extendido", unique = true)
    private String codigoBarrasExtendido;

    @Column(nullable = false)
    private String descripcion;

    // Precios
    private BigDecimal precioPublico;
    private BigDecimal precioDescuento;
    private BigDecimal precioFarmacia;

    // Costos
    private BigDecimal costo;
    private BigDecimal costoPromedio;
    private BigDecimal costoReal;

    // Impuestos
    private Integer iva;
    private Integer ieps;
    private Integer ieps2;

    // Flags
    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
    private Boolean antibiotico;
    private Boolean aplicaDescuentoBase;

    // Control
    private LocalDate fechaActualizacionPrecio;
    
}