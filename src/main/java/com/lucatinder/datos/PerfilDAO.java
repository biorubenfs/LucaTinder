package com.lucatinder.datos;

import java.util.List;

import com.lucatinder.modelo.Perfil;

public interface PerfilDAO {
	
	public void agregarPerfil(Perfil perfil);
	public List<Perfil> listarPerfiles();

}
