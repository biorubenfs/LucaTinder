package com.lucatinder.control;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Controlador principal de la aplicación
 * @author David Vigon, Alejandro Jurado, Ruben Fernandez
 * Fecha: 5-6-2020.
 * Gestiona la las páginas /inicio, /registro, /login y /listado
 *
 */
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
	
	@GetMapping("/registro")
	public String nuevoPerfil(ModelMap model) {
		logger.info(">>>>>>>> en la página de registro /registro");
		model.addAttribute("perfil", new Perfil());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String nuevoPerfil(Perfil perfil, BindingResult bindingResult, Model model) {
		logger.info(">>>>>>>> en la página de registro /registro POST");
		Perfil perfilExiste = perfilServicios.findByEmail(perfil.getEmail());	//comentario prueba
		
		if(perfilExiste != null) {
			logger.info(">>>>>>>> el perfil ya existe");
			model.addAttribute("mensaje", "Ese correo ya existe");
			return "registro";
		}
		else {
			logger.info(">>>>>>>> perfil añadido correctamente");
			perfilServicios.agregarPerfil(perfil);
			model.addAttribute("mensaje", "El perfil ha sido creado correctamente");
			return "login";
		}	
	}
	
	@GetMapping("/login")
	public String loginPage() {
		logger.info(">>>>>>>> en la página de login /login");
		return "login";
  }
	

	@GetMapping("/listado")
	public ModelAndView listadoPerfiles(){
		logger.info(">>>>>>>> en la página de listado de perfiles /listado");
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	
	/* Esta parte debe sustituir a la anterior, 
	 * evitando que la sugerencia de contacto sea el propio usuario
	@GetMapping("/listado")
	public ModelAndView listadoPerfiles() {
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	*/

	@RequestMapping("/login")  
    @ResponseBody
	public ModelAndView listadoPerfiles1(){
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
}
