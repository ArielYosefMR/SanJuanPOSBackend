package com.sanjuan.pos.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanjuan.pos.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
	
	Optional<Proveedor> findByRFC(String rfc);
	@Query("SELECT SUM(p.importe) FROM Proveedor p")
	BigDecimal obtenerSumaImportes();

}
