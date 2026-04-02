package com.sanjuan.pos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sanjuan.pos.dto.ColorDTO;
import com.sanjuan.pos.entity.Color;
import com.sanjuan.pos.repository.ColorRepository;
import com.sanjuan.pos.service.ColorServiceInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorServiceInterface{

	private final ColorRepository colorRepository;
	
	@Override
	public List<Color> listarColores() {
		return colorRepository.findAll();
	}

	@Override
	public Color crearColor(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public Color editarColor(Long id, ColorDTO color) {
		Color editar = colorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Color no existe"));
		if(color.getColor() != null) editar.setColor(color.getColor());
		if(color.getPorcentaje() != null) editar.setPorcentaje(color.getPorcentaje());
		
		return colorRepository.save(editar);
	}

	@Override
	public void eliminarColor(Long id) {
		colorRepository.deleteById(id);
	}

}
