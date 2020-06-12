package com.lucatinder.datos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
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
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Contacto> listarContactos(){
		String hql = "select * from Perfil where id_perfil in (select id_perfil2 from Contacto where id_perfil1=?)";
		return (List<Contacto>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Descarte> listarDescartes(int id_perfil1, int id_perfil2){
		String hql = "select * from Perfil where id_perfil in (select id_perfil2 from Descarte where id_perfil1=?)";
		return (List<Descarte>) entityManager.createQuery(hql).getResultList();
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
	
	@Override
	public void agregarContacto(Perfil perfil1, Perfil perfil2) {
		Contacto contacto = new Contacto();
		contacto.setId_perfil1(perfil1.getId());
		contacto.setId_perfil2(perfil2.getId());
		entityManager.merge(contacto);
	}
	
	@Override
	public void agregarDescarte(Perfil perfil1, Perfil perfil2) {
		Descarte descarte = new Descarte();
		descarte.setId_perfil1(perfil1.getId());
		descarte.setId_perfil2(perfil2.getId());
		entityManager.merge(descarte);
	}
	
}