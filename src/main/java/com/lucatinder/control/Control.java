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
		Perfil perfilExiste = perfilServicios.findByEmail(perfil.getEmail());	//comentario prueba
		
		if(perfilExiste != null) {
			model.addAttribute("mensaje", "Ese correo ya existe");
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
	

	@GetMapping("/listado")
	public ModelAndView listadoPerfiles(){
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
