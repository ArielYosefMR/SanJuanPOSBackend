package com.sanjuan.pos.service;

import com.sanjuan.pos.entity.Medico;

public interface MedicoServiceInterface {
	
	Medico obtenerPorCedula(String cedula);
	Medico agregarMedico(Medico medico);
	Medico editarMedico(String cedula, Medico medico);
	
}
