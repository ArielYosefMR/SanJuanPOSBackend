package com.sanjuan.pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjuan.pos.service.ColorServiceInterface;
import com.sanjuan.pos.dto.ColorDTO;
import com.sanjuan.pos.entity.Color;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {
	private final ColorServiceInterface colorServiceInterface;
	
	@GetMapping
	public List<Color> listarColores(){
		return colorServiceInterface.listarColores();
	}
	
	@GetMapping("/edit/{id}")
	public Optional<Color> consultarColor(@PathVariable Long id){
		return colorServiceInterface.consultarColor(id);
	}
	
	@PostMapping
	public Color crearColor(@RequestBody Color color) {
		return colorServiceInterface.crearColor(color);
	}
	
	@PatchMapping("/edit/{id}")
	public Color actualizarColor(@PathVariable  Long id, @RequestBody ColorDTO color) {
		return colorServiceInterface.editarColor(id, color);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarColor(@PathVariable Long id) {
		colorServiceInterface.eliminarColor(id);
	}
	
}
