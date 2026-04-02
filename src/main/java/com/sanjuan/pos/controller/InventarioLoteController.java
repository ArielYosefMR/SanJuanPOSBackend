package com.sanjuan.pos.controller;

import com.sanjuan.pos.dto.InventarioLoteRequestDTO;
import com.sanjuan.pos.entity.InventarioLote;
import com.sanjuan.pos.service.InventarioLoteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/lotes")
@RequiredArgsConstructor
public class InventarioLoteController {
/*
    private final InventarioLoteServiceInterface service;

    @PostMapping
    public InventarioLote agregarLote(@RequestBody InventarioLoteRequestDTO dto) {

        return service.agregarLote(
                dto.getProductoId(),
                dto.getLote(),
                dto.getCantidad(),
                LocalDate.parse(dto.getFechaCaducidad())
        );
    }

    @GetMapping("/producto/{productoId}")
    public List<InventarioLote> obtenerLotesDisponibles(@PathVariable Long productoId) {
        return service.obtenerLotesDisponibles(productoId);
    }

    @PutMapping("/salida/{loteId}")
    public InventarioLote descontar(
            @PathVariable Long loteId,
            @RequestParam Integer cantidad) {

        return service.descontarStockPorLote(loteId, cantidad);
    }*/
}