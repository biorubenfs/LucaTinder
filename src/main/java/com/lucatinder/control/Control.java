package com.lucatinder.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@Controller
public class Control {

	@Autowired
	private PerfilServicios perfilServicios;
	
	
	public Control(PerfilServicios perfilServicios){
		this.perfilServicios=perfilServicios;
	}

	@GetMapping("/")
	public String paginaInicio() {
		return "inicio";
	}
	
	@GetMapping("/registro")
	public String nuevoPerfil(ModelMap model) {
		model.addAttribute("perfil", new Perfil());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String nuevoPerfil(Perfil perfil, BindingResult bindingResult, Model model) {
		Perfil perfilExiste = perfilServicios.findByUsername(perfil.getNombre());
		
		if(perfilExiste != null) {
			model.addAttribute("mensaje", "El nombre de usuario ya existe");
			return "registro";
		}
		else {
			perfilServicios.agregarPerfil(perfil);
			model.addAttribute("mensaje", "El perfil ha sido creado correctamente");
			return "login";
		}	
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
		
	// Un método para obtener un listado (de perfiles de momento)
	@GetMapping("/listado")
	public ModelAndView listadoPerfiles(){
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	
	
	@RequestMapping("/login")  
    @ResponseBody
	public ModelAndView listadoPerfiles1(){
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	
}
