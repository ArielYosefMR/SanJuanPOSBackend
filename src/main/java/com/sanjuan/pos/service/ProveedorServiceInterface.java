package com.sanjuan.pos.service;

import com.sanjuan.pos.entity.Proveedor;

import java.util.List;

public interface ProveedorServiceInterface {
    Proveedor crearProveedor(Proveedor proveedor);
    List<Proveedor> listarProveedores();
}