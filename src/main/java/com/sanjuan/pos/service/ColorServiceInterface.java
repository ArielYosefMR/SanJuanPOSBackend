package com.sanjuan.pos.service;

import java.util.List;
import java.util.Optional;

import com.sanjuan.pos.dto.ColorDTO;
import com.sanjuan.pos.entity.Color;

public interface ColorServiceInterface {
	List<Color> listarColores();
	Optional<Color>consultarColor(Long id);
	Color crearColor(Color color);
	Color editarColor(Long id, ColorDTO color);
	void eliminarColor(Long id);
}
