package com.sanjuan.pos.controller;

import com.sanjuan.pos.dto.ProveedorDTO;
import com.sanjuan.pos.entity.Proveedor;
import com.sanjuan.pos.service.ProveedorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    
    @GetMapping("/consultar/{id}")
    public ResponseEntity<Optional<Proveedor>> consultarProveedor(@PathVariable Long id){
    	return ResponseEntity.ok(proveedorService.consultarProveedor(id));
    }
    
    @PatchMapping("/editar/{id}")
    public ResponseEntity<Proveedor> editarProveedor(@PathVariable Long id, 
    		@RequestBody ProveedorDTO proveedorDTO){
    	return ResponseEntity.ok(proveedorService.editarProveedor(id, proveedorDTO));
    }
    
    @DeleteMapping("/{id}")
    public void elimiarProveedor(@PathVariable Long id){
    	proveedorService.eliminarProveedor(id);
    }
}