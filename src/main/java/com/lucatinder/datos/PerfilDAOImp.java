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

	@Override
	public void agregarPerfil(Perfil perfil) {
		entityManager.merge(perfil);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Perfil> listarPerfiles() {
		String hql = "FROM Perfil ORDER BY RAND()";
		return (List<Perfil>) entityManager.createQuery(hql).getResultList();
	}

}