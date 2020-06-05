package com.lucatinder.datos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatinder.modelo.Perfil;

@Repository
public class PerfilDAOImp implements PerfilDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Metodo que agrega un nuevo registro de perfil a la base de datos
	 * en la tabla perfiles.
	 */
	@Override
	public void agregarPerfil(Perfil perfil) {
		entityManager.merge(perfil);
	}


	/**
	 * Metodo que devuelve una lista con todos los perfiles en la base de datos
	 * Hay que modificarlo para que devuelva un perfil aleatorio.
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Perfil> listarPerfiles() {
		String hql = "FROM Perfil ORDER BY RAND()";
		return (List<Perfil>) entityManager.createQuery(hql).getResultList();
	}
}