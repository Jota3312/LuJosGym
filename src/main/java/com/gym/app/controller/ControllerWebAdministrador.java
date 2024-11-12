package com.gym.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.gym.app.entity.Administrador;
import com.gym.app.repository.AdministradorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class ControllerWebAdministrador {
    
    @Autowired
    private AdministradorRepository administradorRepository;

   
    @GetMapping("/index")
    public String coordinadorIndexTemplate(Model model, HttpSession session) {
        // Obtener el usuario logeado de la sesión
    	Administrador administrador = (Administrador) session.getAttribute("usuarioLogeado");
        
        // Verificar si el usuario está logeado antes de agregarlo al modelo
        if (administrador != null) {
            model.addAttribute("usuario", administrador.getUsuario());
            model.addAttribute("nombre", administrador.getNombre());
        }
        
        return "index-administrador";
    }
    
    @GetMapping("/login")
    public String administradorLoginTemplate(Model model) {
        return "login-administrador";
    }
    
    @PostMapping("/logear")
    public String administradorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        // Buscar al coordinador por nombre de usuario en la base de datos
    	Administrador administrador = null;
        for (Administrador c : administradorRepository.findAll()) {
            if (c.getUsuario().equals(usuario)) {
            	administrador = c;
                break;
            }
        }
        
        // Verificar si se encontró al coordinador y si la contraseña es correcta
        if (administrador != null && administrador.getContrasena().equals(contrasena)) {
            // Guardar el usuario logeado en la sesión
            session.setAttribute("usuarioLogeado", administrador);
            // Si las credenciales son correctas, redirigir a la página de inicio
            return "redirect:/administrador/index";
        } else {
            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
            model.addAttribute("error", true);
            return "login-administrador";
        }
    }
}