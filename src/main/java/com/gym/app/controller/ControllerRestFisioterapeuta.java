package com.gym.app.controller;

import com.gym.app.entity.Fisioterapeuta;
import com.gym.app.repository.FisioterapeutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fisioterapeutas")
public class ControllerRestFisioterapeuta {

    @Autowired
    private FisioterapeutaRepository fisioterapeutaRepository;

    @GetMapping
    public List<Fisioterapeuta> listarFisioterapeutas() {
        return fisioterapeutaRepository.findAll();
    }

    @PostMapping
    public Fisioterapeuta crearFisioterapeuta(@RequestBody Fisioterapeuta fisioterapeuta) {
        return fisioterapeutaRepository.save(fisioterapeuta);
    }

    @PutMapping("/{id}")
    public Fisioterapeuta actualizarFisioterapeuta(@PathVariable String id, @RequestBody Fisioterapeuta fisioterapeutaActualizado) {
        fisioterapeutaActualizado.setId(id);
        return fisioterapeutaRepository.save(fisioterapeutaActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarFisioterapeuta(@PathVariable String id) {
        fisioterapeutaRepository.deleteById(id);
    }
}
