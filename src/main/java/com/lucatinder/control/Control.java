package com.lucatinder.control;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	/*@GetMapping("/registro")
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
	}*/
	
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
	
	
	
	/*@GetMapping("/login")
	public String loginPage() {
		logger.info(">>>>>>>> en la página de login /login");
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView loginPage1() {
		logger.info(">>>>>>>> login realizado");
		return new ModelAndView("redirect:/listado");
	}*/
	
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
	    if (error != null)
	        model.addAttribute("error", "Your username and password is invalid.");	   	    	
	    if (logout != null)
	        model.addAttribute("message", "You have been logged out successfully.");
	    return "login";
	}
	

	/*@GetMapping("/listado")
	public ModelAndView listadoPerfiles(){
		logger.info(">>>>>>>> en la página de listado de perfiles /listado");
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}*/
	
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
	
	/* Esta parte debe sustituir a la anterior, 
	 * evitando que la sugerencia de contacto sea el propio usuario
	@GetMapping("/listado")
	public ModelAndView listadoPerfiles() {
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	
	// Esta parte parecía que no funcionaba bien.
	@RequestMapping("/login")  
    @ResponseBody
	public ModelAndView listadoPerfiles1(){
		List<Perfil> listadoPerfiles = perfilServicios.listarPerfil();
		ModelAndView model = new ModelAndView("listado");
		model.addObject("listadoPerfiles", listadoPerfiles);
		return model;
	}
	*/
}
