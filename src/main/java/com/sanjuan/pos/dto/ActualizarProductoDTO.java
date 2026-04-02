package com.sanjuan.pos.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarProductoDTO {

    private String descripcion;
    private Long colorId;               
    private BigDecimal precioPublico;
    private BigDecimal precioDescuento;
    private BigDecimal precioFarmacia;
    private BigDecimal ultimoCosto;
    private Boolean iva;
    private Integer ieps;
    private Boolean antibiotico;
    private Boolean actualizable;
    private String equivalencia;
    private Integer ssa;
    private String laboratorio;
    private String cls;
    private Boolean zona;
    private Boolean pareto;
    private Boolean presentacion;
    private String proveedor1;
    private String proveedor2;
    private String proveedor3;
    
}