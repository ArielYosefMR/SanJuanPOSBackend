package com.sanjuan.pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjuan.pos.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Optional<Medico> findByCedula(String cedula);

}
