package com.lucatinder.datos;

import java.util.List;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
import com.lucatinder.modelo.Juntos;
import com.lucatinder.modelo.Perfil;

public interface PerfilDAO {
	
	// Interfaz que se encarga de llamar a las funciones del perfil 
	public void agregarPerfil(Perfil perfil);
	public List<Perfil> listarPerfiles();
	public Perfil get(int id);
	//public List<Perfil> listarPerfiles(Perfil perfil);
	
	public void agregarContacto(Perfil perfil1, Perfil perfil2);
	public void agregarDescarte(Perfil perfil1, Perfil perfil2);
	
	public List<Contacto> listarContactos(int id_perfil);
	public List<Descarte> listarDescartes(int id_perfil);
	
	//public void agregarMatch(Perfil perfil1, Perfil perfil2);

	public List<Juntos> listarMatch(int id_perfil);

}
