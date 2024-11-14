package com.gym.app.controller;

import com.gym.app.entity.Entrenador;
import com.gym.app.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class ControllerRestEntrenador {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping
    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @PostMapping
    public Entrenador crearEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @PutMapping("/{id}")
    public Entrenador actualizarEntrenador(@PathVariable String id, @RequestBody Entrenador entrenadorActualizado) {
        entrenadorActualizado.setId(id);
        return entrenadorRepository.save(entrenadorActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEntrenador(@PathVariable String id) {
        entrenadorRepository.deleteById(id);
    }
}
