package com.sanjuan.pos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalle_compra")
@Getter
@Setter
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    private String codigo;

    private String descripcion;

    private Integer cantidad;

    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(name = "costo_anterior", precision = 15, scale = 2)
    private BigDecimal costoAnterior;

    @Column(precision = 15, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal iva;

    private String lote;

    private LocalDate caducidad;
}