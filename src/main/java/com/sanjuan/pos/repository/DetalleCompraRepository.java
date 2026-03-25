package com.sanjuan.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanjuan.pos.entity.DetalleCompra;

import java.util.List;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {

    List<DetalleCompra> findByCompraId(Long compraId);
}