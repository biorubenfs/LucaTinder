package com.lucatinder.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@Controller
public class Control {

	@Autowired
	private PerfilServicios perfilServicios;
	
	
	public Control(PerfilServicios perfilServicios){
		this.perfilServicios=perfilServicios;
	}

	@GetMapping("/inicio")
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

}
