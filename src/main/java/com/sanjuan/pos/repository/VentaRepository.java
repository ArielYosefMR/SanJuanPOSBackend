package com.sanjuan.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjuan.pos.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
