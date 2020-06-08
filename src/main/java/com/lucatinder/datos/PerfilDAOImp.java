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
	 * Metodo que devuelve una lista con 5 perfiles de la base de datos
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Perfil> listarPerfiles() {
		String hql = "FROM Perfil ORDER BY RAND()";
		return (List<Perfil>) entityManager.createQuery(hql).setMaxResults(5).getResultList();
	}
	
	
	/**
	 * Metodo que devuelve una lista con 5 perfiles de la base de datos
	 * Debe evitar que en la lista devuelta se encuentre el propio usuario
	 */
	/*
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Perfil> listarPerfiles(Perfil perfil) {
		String hql = "FROM Perfil ORDER BY RAND()";
		List<Perfil> lista = entityManager.createQuery(hql).setMaxResults(5).getResultList();
		for(Perfil i : lista) {
			if(i.getEmail().equalsIgnoreCase(perfil.getEmail()) {
				lista.remove(perfil);
			}
		}
		return lista;
	}
	*/

	
	//Mejora del método anterior
	
	public Perfil get(int id) {
		return entityManager.find(Perfil.class, id);
	}
}