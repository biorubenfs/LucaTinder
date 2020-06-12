package com.lucatinder.servicios;

import java.util.List;

import com.lucatinder.modelo.Perfil;

public interface PerfilServicios {
	
	public void agregarPerfil(Perfil perfil);
	public Perfil findByNombre(String nombre);
	public Perfil findByPassword(String pasword);
	public void salvarPerfil(Perfil perfil);
	public List<Perfil> listarPerfil();
	public Perfil get(int id);
	public Perfil findByEmail(String email);
	
	public void agregarContacto(Perfil perfil1, Perfil perfil2);
	
	// prueba
	//public List<Perfil> listarPerfiles(Perfil perfil);

}

