package com.lucatinder.control;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
import com.lucatinder.modelo.Juntos;
import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

/**
 * Controlador principal de la aplicación
 * @author David Vigon, Alejandro Jurado, Ruben Fernandez
 * Fecha: 5-6-2020.
 * Gestiona la las páginas /inicio, /registro, /login y /listado
 *
 */

// Clase Control que se encarga de gestionar los métodos y enlaces de la aplicación
@Controller
public class Control {

	@Autowired
	private PerfilServicios perfilServicios;
	
	private static final Logger logger = LoggerFactory.getLogger(Control.class);
	
	public Control(PerfilServicios perfilServicios){
		this.perfilServicios=perfilServicios;
	}

	@GetMapping("/")
	public String paginaInicio() {
		logger.info(">>>>>>>> en la página de inicio /");
		return "inicio";
	}
	
	
	@RequestMapping(value="/registro", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Perfil per = new Perfil();
        modelAndView.addObject("perfil", per);
        modelAndView.setViewName("registro");
        return modelAndView;
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Validated Perfil per, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Perfil userExists = perfilServicios.findByNombre(per.getNombre());
        if (userExists != null) {
            bindingResult.rejectValue("Nombre", "error.perfil", "Este perfil ya esta registrado");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
        	perfilServicios.salvarPerfil(per);
            modelAndView.addObject("successMessage", "Registro satisfactorio");
            modelAndView.addObject("perfil", new Perfil());
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
	
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
	    if (error != null)
	        model.addAttribute("error", "Your username and password is invalid.");	   	    	
	    if (logout != null)
	        model.addAttribute("message", "You have been logged out successfully.");
	    return "login";
	}
	
	@RequestMapping(value="/listado", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Usuario user = usuario.findByNombre(auth.getName());
        List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
        modelAndView.addObject("listadoPerfiles", listadoPerfiles);
        modelAndView.setViewName("listado");
        return modelAndView;	    	
    }
	
	@GetMapping("/listado/contactos/{idContacto}")
	public String agregarContacto(@PathVariable("idContacto") int idContacto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Perfil user = perfilServicios.findByNombre(auth.getName());
		logger.info(">>>>>>>>>>>>> Usuario logeado: " + user.getId());
		logger.info("Le das like a " + idContacto);
		perfilServicios.agregarContacto(perfilServicios.get(user.getId()), perfilServicios.get(idContacto));
		return "redirect:/listado";
	}
	
	@GetMapping("/listado/descartes/{idDescarte}")
	public String agregarDescarte(@PathVariable("idDescarte") int idDescarte) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Perfil user = perfilServicios.findByNombre(auth.getName());
		logger.info(">>>>>>>>>>>>> Usuario logeado: " + user.getId());
		logger.info("Le das dislike a " + idDescarte);
		perfilServicios.agregarDescarte(perfilServicios.get(user.getId()), perfilServicios.get(idDescarte));
		return "redirect:/listado";
	}
	
	@RequestMapping(value="/descartados", method = RequestMethod.GET)
    public ModelAndView Descartes(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Perfil user = perfilServicios.findByNombre(auth.getName());
        List<Descarte> listadoDescarte = perfilServicios.listarDescartes(user.getId());
        modelAndView.addObject("listadoPerfiles", listadoDescarte);
        modelAndView.setViewName("descartados");
        return modelAndView;	    	
    }
	
	@RequestMapping(value="/contactos", method = RequestMethod.GET)
    public ModelAndView Contactos(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Perfil user = perfilServicios.findByNombre(auth.getName());
        List<Contacto> listadoContactos = perfilServicios.listarContactos(user.getId());
        modelAndView.addObject("listadoPerfiles", listadoContactos);
        modelAndView.setViewName("contactados");
        return modelAndView;	    	
    }
	
	@RequestMapping(value="/matches", method = RequestMethod.GET)
    public ModelAndView Match(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Perfil user = perfilServicios.findByNombre(auth.getName());
		List<Juntos> listadoMatch = perfilServicios.listarMatch(user.getId());
        modelAndView.addObject("listadoPerfiles", listadoMatch);
        modelAndView.setViewName("matches");
        return modelAndView;	    	
    }
	
}
