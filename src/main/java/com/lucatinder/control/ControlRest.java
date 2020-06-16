package com.lucatinder.control;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
import com.lucatinder.modelo.Perfil;
import com.lucatinder.servicios.PerfilServicios;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	@GetMapping("/listar/{email}")
	public Perfil listarPerfil(@PathVariable("email") String email) {
		logger.info(">>>>>>>> listado perfiles REST /");
		return perfilServicios.findByEmail(email);
	}

	@PostMapping("/alta")
	public Perfil agregarPerfil(@RequestBody Perfil perfil) {
		logger.info(">>>>>>>> agregado perfil REST  ");
		perfilServicios.agregarPerfil(perfil);
		return perfil;
	}
	
	/*
	 * Hay que logearse primero para poder acceder a la consulta
	 */
	@GetMapping("/listarContactos/{idContacto}")
	public Collection<Contacto> listarContactos(@PathVariable("idContacto") int id_perfil){
		logger.info(">>>>>>>> listado contacto REST /");
		return perfilServicios.listarContactos(id_perfil);
	}
	

	/*
	 * Hay que logearse primero para poder acceder a la consulta
	 */
	@GetMapping("/listarDescartes/{idDescarte}")
	public Collection<Descarte> listarDescartes(@PathVariable("idDescarte") int id_perfil){
		logger.info(">>>>>>>> listado descarte REST /");
		return perfilServicios.listarDescartes(id_perfil);
	}
	
	@PostMapping("/altaContacto")
	public void agregarContacto(@RequestBody Map<String, Integer> json) {
		logger.info(">>>>>>>> agregado contacto REST  ");
		perfilServicios.agregarContacto(perfilServicios.get(json.get("id_perfil1")), perfilServicios.get(json.get("id_perfil2")));			
	}
	
	@PostMapping("/altaDescarte")
	public void agregarDescarte(@RequestBody Map<String, Integer> json) {
		logger.info(">>>>>>>> agregado contacto REST  ");
		perfilServicios.agregarContacto(perfilServicios.get(json.get("id_perfil1")), perfilServicios.get(json.get("id_perfil2")));			
	}
	
	@RequestMapping(value="/login/{email}", method = RequestMethod.GET)
	public Perfil logger(@PathVariable("email") String email) {
		Perfil perfil=perfilServicios.findByEmail(email);
		return perfil;
	}
}
