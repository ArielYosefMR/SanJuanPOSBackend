package com.sanjuan.pos.service;

import com.sanjuan.pos.entity.InventarioLote;

import java.time.LocalDate;
import java.util.List;

public interface InventarioLoteServiceInterface {

    InventarioLote agregarLote(Long productoId, String lote, Integer cantidad, LocalDate fechaCaducidad);

    List<InventarioLote> obtenerLotesDisponibles(Long productoId);
    
    InventarioLote obtenerLotePorId(Long loteId);

    InventarioLote descontarStockPorLote(Long loteId, Integer cantidad);

    Integer obtenerStockTotal(Long productoId);
}