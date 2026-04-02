package com.sanjuan.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_barras", unique = true, nullable = false)
    private String codigoBarras;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

    @Column(name = "precio_publico", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioPublico;

    @Column(name = "precio_descuento", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioDescuento;

    @Column(name = "precio_farmacia", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioFarmacia;

    @Column(name = "ultimo_costo", nullable = false, precision = 10, scale = 2)
    private BigDecimal ultimoCosto;

    @Column(name = "iva", nullable = false)
    private Boolean iva;

    @Column(name = "ieps", nullable = false)
    @Builder.Default
    private Integer ieps = 0;

    @Column(name = "habilitado", nullable = false)
    @Builder.Default
    private Boolean habilitado = true;

    @Column(name = "antibiotico", nullable = false)
    private Boolean antibiotico;

    @Column(name = "actualizable", nullable = false)
    private Boolean actualizable;

    @Column(name = "equivalencia", nullable = false)
    private String equivalencia;

    @Column(name = "ssa", nullable = false)
    private Integer ssa;

    @Column(name = "laboratorio", nullable = false)
    private String laboratorio;

    @Column(name = "cls", nullable = false)
    private String cls;

    @Column(name = "zona", nullable = false)
    private Boolean zona;

    @Column(name = "pareto", nullable = false)
    private Boolean pareto;

    @Column(name = "presentacion", nullable = false)
    private Boolean presentacion;

    @Column(name = "proveedor1", nullable = false)
    private String proveedor1;

    @Column(name = "proveedor2", nullable = false)
    private String proveedor2;

    @Column(name = "proveedor3", nullable = false)
    private String proveedor3;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

}