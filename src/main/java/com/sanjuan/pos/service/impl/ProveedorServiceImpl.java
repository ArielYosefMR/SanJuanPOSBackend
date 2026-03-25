package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.entity.Proveedor;
import com.sanjuan.pos.repository.ProveedorRepository;
import com.sanjuan.pos.service.ProveedorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorServiceInterface {

    private final ProveedorRepository proveedorRepository;

    @Override
    public Proveedor crearProveedor(Proveedor proveedor) {
        if (proveedorRepository.findByRFC(proveedor.getRFC()).isPresent()) {
            throw new RuntimeException("El RFC ya existe: " + proveedor.getRFC());
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }
}