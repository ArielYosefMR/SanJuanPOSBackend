package com.sanjuan.pos.service;

import com.sanjuan.pos.dto.ProveedorDTO;
import com.sanjuan.pos.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorServiceInterface {
    Proveedor crearProveedor(Proveedor proveedor);
    List<Proveedor> listarProveedores();
    Optional<Proveedor> consultarProveedor(Long id);
    Proveedor editarProveedor(Long id, ProveedorDTO proveedorDTO);
    void eliminarProveedor(Long id);
}