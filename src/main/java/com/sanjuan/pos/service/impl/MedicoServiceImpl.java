package com.sanjuan.pos.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sanjuan.pos.entity.Medico;
import com.sanjuan.pos.repository.MedicoRepository;
import com.sanjuan.pos.service.MedicoServiceInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoServiceInterface {
	
	MedicoRepository medicoRepository;

	@Override
	public Medico obtenerPorCedula(String cedula) {
	    return medicoRepository.findByCedula(cedula)
	        .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
	}

	@Override
	public Medico agregarMedico(Medico medico) {

	    Optional<Medico> existente = medicoRepository.findByCedula(medico.getCedula());

	    if (existente.isPresent()) {
	        throw new RuntimeException("Ya existe un médico con esa cédula");
	    }

	    return medicoRepository.save(medico);
	}

	@Override
	public Medico editarMedico(String cedula, Medico medico) {
	    Medico existente = medicoRepository.findByCedula(cedula)
	        .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

	    if (medico.getNombre() != null) {
	        existente.setCedula(medico.getCedula());
	    }
	    if (medico.getNombre() != null) {
	        existente.setNombre(medico.getNombre());
	    }
	    if (medico.getNombre() != null) {
	        existente.setDomicilio(medico.getDomicilio());
	    }

	    return medicoRepository.save(existente);
	}

}
