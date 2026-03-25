package com.sanjuan.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjuan.pos.entity.Medico;
import com.sanjuan.pos.service.MedicoServiceInterface;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoServiceInterface medicoService;

    @GetMapping("/cedula/{cedula}")
    public Medico obtenerPorCedula(@PathVariable String cedula) {
        return medicoService.obtenerPorCedula(cedula);
    }

    @PostMapping
    public Medico crear(@RequestBody Medico medico) {
        return medicoService.agregarMedico(medico);
    }

    @PutMapping("/{id}")
    public Medico actualizar(@PathVariable String cedula, @RequestBody Medico medico) {
        return medicoService.editarMedico(cedula, medico);
    }

}