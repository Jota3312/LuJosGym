package com.gym.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.gym.app.entity.Administrador;
import com.gym.app.repository.AdministradorRepository;
import com.gym.app.repository.EntrenadorRepository;
import com.gym.app.repository.FisioterapeutaRepository;
import com.gym.app.repository.UsuarioRepository;
import com.gym.app.entity.Usuario;
import com.gym.app.entity.Fisioterapeuta;
import com.gym.app.entity.Entrenador;
import com.gym.app.exception.NotFoundException;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "administrador")
public class ControllerWebAdministrador {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FisioterapeutaRepository fisioterapeutaRepository;
   
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
    
    
    @GetMapping("/entrenador/crear")
	public String entrenadorCrearTemplate(Model model) {
	    model.addAttribute("entrenador", new Entrenador());
	    return "entrenador-form";
	}

	@GetMapping("/lista")
	public String entrenadorListTemplate(Model model) {
	    model.addAttribute("entrenadores", entrenadorRepository.findAll());
	    return "entrenador-lista";
	}

	@GetMapping("/entrenador/edit/{id}")
	public String entrenadorEditTemplate(@PathVariable("id") String id, Model model) {
	    model.addAttribute("entrenador",
	    		entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("entrenador no encontrado")));
	    return "entrenador-form";
	}

	@PostMapping("/entrenador/save")
	public String entrenadorSaveProcess(@ModelAttribute("entrenador") Entrenador entrenador) {
	    if (entrenador.getId().isEmpty()) {
	    	entrenador.setId(null);
	    }
	    entrenadorRepository.save(entrenador);
	    return "redirect:/administrador/index";
	}

	@GetMapping("/entrenador/delete/{id}")
	public String entrenadorDeleteProcess(@PathVariable("id") String id) {
		entrenadorRepository.deleteById(id);
	    return "redirect:/administrador/lista";
	}
	
	
	
	
	 
	
	  @GetMapping("/usuario/crear")
		public String usuarioCrearTemplate(Model model) {
		    model.addAttribute("usuario", new Usuario());
		    return "usuario-form";
		}

		@GetMapping("/lista2")
		public String usuarioListTemplate(Model model) {
		    model.addAttribute("usuarios", usuarioRepository.findAll());
		    return "usuario-lista";
		}

		@GetMapping("/usuario/edit/{id}")
		public String usuarioEditTemplate(@PathVariable("id") String id, Model model) {
		    model.addAttribute("usuario",
		    		usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("usuario no encontrado")));
		    return "usuario-form";
		}

		@PostMapping("/usuario/save")
		public String usuarioSaveProcess(@ModelAttribute("usuario") Usuario usuario) {
		    if (usuario.getId().isEmpty()) {
		    	usuario.setId(null);
		    }
		    usuarioRepository.save(usuario);
		    return "redirect:/administrador/index";
		}

		@GetMapping("/usuario/delete/{id}")
		public String usuarioDeleteProcess(@PathVariable("id") String id) {
			usuarioRepository.deleteById(id);
		    return "redirect:/administrador/lista2";
		}
		
		
		
		
		
		
		  @GetMapping("/fisioterapeuta/crear")
			public String fisioterapeutaCrearTemplate(Model model) {
			    model.addAttribute("fisioterapeuta", new Fisioterapeuta());
			    return "fisioterapeuta-form";
			}

		  
			@GetMapping("/lista3")
			public String fisioterapeutaListTemplate(Model model) {
			    model.addAttribute("fisioterapeutas", fisioterapeutaRepository.findAll());
			    return "fisioterapeuta-lista";
			}

			@GetMapping("/fisioterapeuta/edit/{id}")
			public String fisioterapeutaEditTemplate(@PathVariable("id") String id, Model model) {
			    model.addAttribute("fisioterapeuta",
			    		fisioterapeutaRepository.findById(id).orElseThrow(() -> new NotFoundException("fisioterapeuta no encontrado")));
			    return "fisioterapeuta-form";
			}

			@PostMapping("/fisioterapeuta/save")
			public String fisioterapeutaSaveProcess(@ModelAttribute("fisioterapeuta") Fisioterapeuta fisioterapeuta) {
			    if (fisioterapeuta.getId().isEmpty()) {
			    	fisioterapeuta.setId(null);
			    }
			    fisioterapeutaRepository.save(fisioterapeuta);
			    return "redirect:/administrador/index";
			}

			@GetMapping("/fisioterapeuta/delete/{id}")
			public String fisioterapeutaDeleteProcess(@PathVariable("id") String id) {
				fisioterapeutaRepository.deleteById(id);
			    return "redirect:/administrador/lista3";
			}
			
			
			
	
}