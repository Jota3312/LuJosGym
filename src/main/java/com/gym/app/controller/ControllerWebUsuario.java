package com.gym.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.app.entity.Usuario;
import com.gym.app.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "usuario")
public class ControllerWebUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    @GetMapping("/index")
    public String entrenadorIndexTemplate(Model model, HttpSession session) {
    	Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuario != null) {
            model.addAttribute("nombre", usuario.getNombres());
            model.addAttribute("apellido", usuario.getApellidos());
        }
        
        return "index-usuario";
    }
    
    @PostMapping("/logear")
    public String usuarioLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Usuario user = null;
        for (Usuario u : usuarioRepository.findAll()) {
            if (u.getUsuario().equals(usuario)) {
                user = u;
                break;
            }
        }

        if (user != null && user.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", user);
            return "redirect:/usuario/index";
        } else {
            model.addAttribute("error", true);
            return "login-usuario";
        }
    }

    @GetMapping("/login")
    public String usuarioLoginTemplate(Model model) {
        return "login-usuario";
    }
}
