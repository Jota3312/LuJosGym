package com.gym.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.app.entity.Entrenador;
import com.gym.app.repository.EntrenadorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "entrenador")
public class ControllerWebEntrenador {
	
	
	  @GetMapping("/index")
	    public String entrenadorIndexTemplate(Model model, HttpSession session) {
	        Entrenador entrenador = (Entrenador) session.getAttribute("usuarioLogeado");

	        if (entrenador != null) {
	            model.addAttribute("nombre", entrenador.getNombres());
	            model.addAttribute("apellido", entrenador.getApellidos());
	        }
	        
	      
	        return "index-entrenador";
	    }

	  

	  @GetMapping("/login")
	    public String entrenadorLoginTemplate(Model model) {
	        return "login-entrenador";
	    }
	  
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @PostMapping("/logear")
    public String entrenadorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Entrenador entrenador = null;
        for (Entrenador e : entrenadorRepository.findAll()) {
            if (e.getUsuario().equals(usuario)) {
                entrenador = e;
                break;
            }
        }

        if (entrenador != null && entrenador.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", entrenador);
            return "redirect:/entrenador/index";
        } else {
            model.addAttribute("error", true);
            return "login-entrenador";
        }
    }

  
}
