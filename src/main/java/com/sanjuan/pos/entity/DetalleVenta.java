package com.sanjuan.pos.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    //Guardar el lote
    @ManyToOne
    @JoinColumn(name = "lote_id")
    private InventarioLote lote;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;
}