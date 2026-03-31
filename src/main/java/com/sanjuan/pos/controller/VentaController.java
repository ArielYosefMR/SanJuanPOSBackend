package com.sanjuan.pos.controller;

import com.sanjuan.pos.dto.VentaRequestDTO;
import com.sanjuan.pos.dto.VentaResponseDTO;
import com.sanjuan.pos.service.VentaServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {
/*
    private final VentaServiceInterface service;

    @PostMapping
    public VentaResponseDTO crear(@RequestBody VentaRequestDTO request) {
        return service.crearVenta(request);
    }
    */
}