package com.lucatinder.servicios;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucatinder.datos.PerfilDAO;
import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
import com.lucatinder.modelo.Perfil;
import com.lucatinder.modelo.Rol;
import com.lucatinder.repositorio.PerfilRepositorio;
import com.lucatinder.repositorio.RolRepositorio;

/**
 * Clase que implementa los métodos de la interfaz PerfilServicios
 * @author David Vigón, Alejandro Jurado, Rubén Fernández
 * Fecha: 5-6-2020
 */
@Service
@Transactional
public class PerfilServiciosImp implements PerfilServicios {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@Autowired
	private RolRepositorio rolRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    public PerfilServiciosImp(PerfilRepositorio perfilRepositorio,
    		RolRepositorio rolRepositorio,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.perfilRepositorio = perfilRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	@Override
	public void agregarPerfil(Perfil perfil) {
		perfilDAO.agregarPerfil(perfil);
	}
	
	@Override
	public Perfil findByNombre(String nombre) {
		return perfilRepositorio.findByNombre(nombre);
	}
	
	@Override
	public Perfil findByPassword(String password) {
		return perfilRepositorio.findByPassword(password);
	}
	
	@Override
	public void salvarPerfil(Perfil perfil) {
		perfil.setPassword(bCryptPasswordEncoder.encode(perfil.getPassword()));
		perfil.setEnabled(true);
		Rol userAdminRole = rolRepositorio.findByRol("ADMIN");
		perfil.setRol(new HashSet<Rol>(Arrays.asList(userAdminRole)));
		perfilRepositorio.save(perfil);
	}

	@Override
	public List<Perfil> listarPerfil() {
		return perfilDAO.listarPerfiles();
	}

	@Override
	public Perfil findByEmail(String email) {
		return perfilRepositorio.findByEmail(email);
	}
	
	@Override
	public Perfil get(int id) {
		return perfilDAO.get(id);
	}
	
	@Override
	public List<Contacto> listarContactos(int id_perfil){
		return perfilDAO.listarContactos(id_perfil);
	}
	
	@Override
	public List<Descarte> listarDescartes(int id_perfil){
		return perfilDAO.listarDescartes(id_perfil);
	}

	/*
	@Override
	public List<Perfil> listarPerfiles(Perfil perfil) {
		return perfilDAO.listarPerfiles(perfil);
	}
	*/
	
	@Override
	public void agregarContacto(Perfil perfil1, Perfil perfil2) {
		perfilDAO.agregarContacto(perfil1, perfil2);
		
	}
	
	@Override
	public void agregarDescarte(Perfil perfil1, Perfil perfil2) {
		perfilDAO.agregarDescarte(perfil1, perfil2);
		
	}
	

}
