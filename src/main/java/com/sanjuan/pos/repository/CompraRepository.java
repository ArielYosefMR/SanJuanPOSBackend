package com.sanjuan.pos.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.sanjuan.pos.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}