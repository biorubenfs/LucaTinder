package com.lucatinder.datos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lucatinder.modelo.Perfil;

@Repository
public class PerfilDAOImp implements PerfilDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarPerfil(Perfil perfil) {
		entityManager.merge(perfil);
	}

}
