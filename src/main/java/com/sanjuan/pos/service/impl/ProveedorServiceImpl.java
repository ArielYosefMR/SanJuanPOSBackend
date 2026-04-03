package com.sanjuan.pos.service.impl;

import com.sanjuan.pos.dto.ProveedorDTO;
import com.sanjuan.pos.entity.Proveedor;
import com.sanjuan.pos.repository.ProveedorRepository;
import com.sanjuan.pos.service.ProveedorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<Proveedor> consultarProveedor(Long id) {
		return proveedorRepository.findById(id);
	}

	@Override
	public Proveedor editarProveedor(Long id, ProveedorDTO proveedorDTO) {
		Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Proveedor no existe"));
		
		if(proveedorDTO.getRFC() != null) proveedor.setRFC(proveedorDTO.getRFC());
		if(proveedorDTO.getNombre() != null) proveedor.setNombre(proveedorDTO.getNombre());
		if(proveedorDTO.getImporte() != null) proveedor.setImporte(proveedorDTO.getImporte());
		proveedorRepository.save(proveedor);
		recalcularPorcentajes();
		return proveedor;
	}

	@Override
	public void eliminarProveedor(Long id) {
		proveedorRepository.deleteById(id);		
	}
	
	private void recalcularPorcentajes() {
	    List<Proveedor> proveedores = proveedorRepository.findAll();

	    BigDecimal total = proveedorRepository.obtenerSumaImportes(); 

	    if (total.compareTo(BigDecimal.ZERO) == 0) {
	        proveedores.forEach(p -> p.setPorcentaje(BigDecimal.ZERO));
	    } else {
	        for (Proveedor p : proveedores) {
	        	BigDecimal porcentaje = p.getImporte()
	        		    .divide(total, 6, RoundingMode.HALF_UP)
	        		    .multiply(BigDecimal.valueOf(100))
	        		    .setScale(2, RoundingMode.HALF_UP)
	        		    .stripTrailingZeros();

	            p.setPorcentaje(porcentaje);
	        }
	    }

	    proveedorRepository.saveAll(proveedores);
	}
}