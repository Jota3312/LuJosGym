package com.gym.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.app.entity.Fisioterapeuta;
import com.gym.app.repository.FisioterapeutaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "fisioterapeuta")
public class ControllerWebFisioterapeuta {

    @Autowired
    private FisioterapeutaRepository fisioterapeutaRepository;
    
    @GetMapping("/index")
    public String fisioterapeutaIndexTemplate(Model model, HttpSession session) {
    	Fisioterapeuta fisioterapeuta = (Fisioterapeuta) session.getAttribute("usuarioLogeado");

        if (fisioterapeuta != null) {
            model.addAttribute("nombre", fisioterapeuta.getNombres());
            model.addAttribute("apellido", fisioterapeuta.getApellidos());
        }
        
        return "index-fisioterapeuta";
    }
    

    @PostMapping("/logear")
    public String fisioterapeutaLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Fisioterapeuta fisioterapeuta = null;
        for (Fisioterapeuta f : fisioterapeutaRepository.findAll()) {
            if (f.getUsuario().equals(usuario)) {
                fisioterapeuta = f;
                break;
            }
        }

        if (fisioterapeuta != null && fisioterapeuta.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogeado", fisioterapeuta);
            return "redirect:/fisioterapeuta/index";
        } else {
            model.addAttribute("error", true);
            return "login-fisioterapeuta";
        }
    }

    @GetMapping("/login")
    public String fisioterapeutaLoginTemplate(Model model) {
        return "login-fisioterapeuta";
    }
}
