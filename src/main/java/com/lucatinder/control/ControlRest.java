package com.lucatinder.control;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@RestController
@RequestMapping("/rperfil")
public class ControlRest {
	
	@Autowired
	private PerfilServicios perfilServicios;
	
	@GetMapping("/listar")
	public Collection<Perfil> listarPerfiles() {
		return perfilServicios.listarPerfil();
	}
}
