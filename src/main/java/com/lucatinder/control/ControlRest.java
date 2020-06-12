package com.lucatinder.control;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@RestController
@RequestMapping("/rperfil")
public class ControlRest {

	@Autowired
	private PerfilServicios perfilServicios;
	
	// A ver si podemos usar aquí la clase Logger.
	private static final Logger logger = LoggerFactory.getLogger(ControlRest.class);

	@GetMapping("/listar")
	public Collection<Perfil> listarPerfiles() {
		logger.info(">>>>>>>> listado perfiles REST /");
		return perfilServicios.listarPerfil();
	}

	@PostMapping("/alta")
	public Perfil agregarPerfil(@RequestBody Perfil perfil) {
		logger.info(">>>>>>>> agregado perfil REST  ");
		perfilServicios.agregarPerfil(perfil);
		return perfil;
	}
	
	@GetMapping("/listarContactos/{idContacto}")
	public Collection<Contacto> listarContactos(@PathVariable("idContacto") int id_perfil){
		logger.info(">>>>>>>> listado contacto REST /");
		System.out.println(id_perfil);
		return perfilServicios.listarContactos(id_perfil);
	}
}
