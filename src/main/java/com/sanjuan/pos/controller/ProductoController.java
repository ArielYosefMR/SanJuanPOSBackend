package com.sanjuan.pos.controller;

import com.sanjuan.pos.dto.ActualizarProductoDTO;
import com.sanjuan.pos.entity.Producto;
import com.sanjuan.pos.service.ProductoServiceInterface;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoServiceInterface service;

    @GetMapping("buscarCodigo/{codigo}")
    public Producto buscarPorCodigo(@PathVariable String codigo){
    	return service.buscarPorCodigo(codigo);
    }
    
    @GetMapping("buscarDescripcion")
    public List<Producto> buscarPorDescripcion(@RequestParam String descripcion){
    	return service.buscarPorDescripcion(descripcion);
    }
    
    @GetMapping("buscarEquivalencia")
    public List<Producto> buscarPorEquivalencia(@RequestParam String equivalencia){
    	return service.buscarPorEquivalencia(equivalencia);
    }
    
    @PostMapping("/agregarProducto")
    public String agregarProducto(@RequestBody Producto producto){
        
        return service.crearProducto(producto);
    }
    
    @PatchMapping("/actualizarProducto/{codigo}")
    public String actualizarProducto(@PathVariable String codigo,
                                    @RequestBody ActualizarProductoDTO dto){
        try {
            service.actualizarProducto(codigo, dto);
            return "Producto actualizado correctamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
    @DeleteMapping("/eliminarProducto/{codigo}")
    public String eliminarProducto(@PathVariable String codigo){
    	return null;
    }
    
    /*
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return service.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarProducto(id);
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return service.obtenerProductoPorId(id);
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listarProductos();
    }

    @GetMapping("/codigo/{codigoBarras}")
    public Producto buscarPorCodigo(@PathVariable String codigoBarras) {
        return service.buscarPorCodigoBarras(codigoBarras);
    }
    
    @GetMapping("/codigo-ext/{codigo}")
    public Producto buscarPorCodigoExtendido(@PathVariable String codigo) {
        return service.buscarPorCodigoBarrasExtendido(codigo);
    }
    
    @PostMapping("/importar")
    public String importarProductos(@RequestParam("file") MultipartFile file) {
        try {
            service.importarProductos(file);
            return "Productos importados correctamente";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }*/
}