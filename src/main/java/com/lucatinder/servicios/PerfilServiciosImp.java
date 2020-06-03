package com.lucatinder.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucatinder.datos.PerfilDAO;
import com.lucatinder.modelo.Perfil;


public class PerfilServiciosImp implements PerfilServicios {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Override
	public void agregarPerfil(Perfil perfil) {
		perfilDAO.agregarPerfil(perfil);
	}
	
	

}
