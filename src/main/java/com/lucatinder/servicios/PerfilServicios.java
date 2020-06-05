package com.lucatinder.servicios;

import java.util.List;

import com.lucatinder.modelo.Perfil;

public interface PerfilServicios {
	
	public void agregarPerfil(Perfil perfil);
	public Perfil findByUsername(String nombre);
	public Perfil findByPassword(String pasword);
	public void salvarPerfil(Perfil perfil);
	public List<Perfil> listarPerfil();

}

