package com.gym.app.controller;

import com.gym.app.entity.Usuario;
import com.gym.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class ControllerRestUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuarioActualizado) {
        usuarioActualizado.setId(id);
        return usuarioRepository.save(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
    }
}
