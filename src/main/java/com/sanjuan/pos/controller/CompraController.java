package com.sanjuan.pos.controller;

import com.sanjuan.pos.dto.CompraDTO;
import com.sanjuan.pos.service.CompraServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {
/*
    private final CompraServiceInterface compraService;

    @PostMapping
    public ResponseEntity<String> guardarCompra(@RequestBody CompraDTO dto) {
        compraService.guardarCompra(dto);
        return ResponseEntity.ok("Compra guardada correctamente");
    }

    @GetMapping
    public ResponseEntity<?> listarCompras() {
        return ResponseEntity.ok(compraService.listarCompras());
    }
    */
}