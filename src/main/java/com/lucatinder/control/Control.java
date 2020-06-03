package com.lucatinder.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@Controller
public class Control {

	@Autowired
	private PerfilServicios perfilServicios;
	
	@GetMapping("/agregarPerfil")
	public String nuevoPerfil(ModelMap model) {
		model.addAttribute("perfil", new Perfil());
		return "AgregarPerfil";
	}
	
	public void hola() {
		
	}

}
