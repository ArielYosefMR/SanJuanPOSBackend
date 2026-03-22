package com.sanjuan.pos.repository;

import com.sanjuan.pos.entity.InventarioLote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventarioLoteRepository extends JpaRepository<InventarioLote, Long> {

    List<InventarioLote> findByProductoIdAndStockGreaterThan(Long productoId, Integer stock);

    List<InventarioLote> findByProductoId(Long productoId);
}