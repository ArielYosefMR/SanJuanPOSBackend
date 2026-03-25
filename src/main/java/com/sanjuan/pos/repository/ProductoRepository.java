package com.sanjuan.pos.repository;

import com.sanjuan.pos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	Optional<Producto> findByCodigoBarrasExtendidoAndActivoTrue(String codigoBarrasExtendido);

	Optional<Producto> findByCodigoBarrasAndActivoTrue(String codigoBarras);

    Optional<Producto> findByClave(String clave);
    
    List<Producto> findByActivoTrue();

    List<Producto> findByActivoFalse();
    
}