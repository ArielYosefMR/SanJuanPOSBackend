package com.sanjuan.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inventario_lote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioLote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private Integer stock;

    private LocalDate fechaCaducidad;
}