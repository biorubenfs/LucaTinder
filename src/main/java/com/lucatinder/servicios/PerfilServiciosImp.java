package com.lucatinder.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatinder.datos.PerfilDAO;
import com.lucatinder.modelo.Perfil;
import com.lucatinder.repositorio.PerfilRepositorio;

@Service
@Transactional
public class PerfilServiciosImp implements PerfilServicios {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@Override
	public void agregarPerfil(Perfil perfil) {
		perfilDAO.agregarPerfil(perfil);
	}
	
	@Override
	public Perfil findByUsername(String nombre) {
		return perfilRepositorio.findByNombre(nombre);
	}
	
	@Override
	public Perfil findByPassword(String password) {
		return perfilRepositorio.findByPassword(password);
	}
	
	@Override
	public void salvarPerfil(Perfil perfil) {
		perfil.setPassword(perfil.getPassword());
		perfil.setNombre(perfil.getNombre());
		perfilRepositorio.save(perfil);
	}

}
