package com.sanjuan.pos.service;

import java.util.List;

import com.sanjuan.pos.dto.CompraDTO;
import com.sanjuan.pos.entity.Compra;

public interface CompraServiceInterface {

	void guardarCompra(CompraDTO dto);
	List<Compra> listarCompras();
}