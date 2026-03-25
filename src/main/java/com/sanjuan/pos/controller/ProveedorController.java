package com.sanjuan.pos.controller;

import com.sanjuan.pos.entity.Proveedor;
import com.sanjuan.pos.service.ProveedorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorServiceInterface proveedorService;

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        Proveedor creado = proveedorService.crearProveedor(proveedor);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> listarProveedores() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }
}