package com.lucatinder.datos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatinder.modelo.Contacto;
import com.lucatinder.modelo.Descarte;
import com.lucatinder.modelo.Juntos;
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
	public List<Contacto> listarContactos(int id_perfil){
		String hql = "From Perfil where id_perfil in (select id_perfil2 from Contacto where id_perfil1="+id_perfil+")";
		return (List<Contacto>) entityManager.createQuery(hql).setMaxResults(8).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Descarte> listarDescartes(int id_perfil){
		String hql = "From Perfil where id_perfil in (select id_perfil2 from Descarte where id_perfil1="+id_perfil+")";
		return (List<Descarte>) entityManager.createQuery(hql).getResultList();
	}
	/**
	 * Metodo que devuelve una lista con 5 perfiles de la base de datos
	 * Debe evitar que en la lista devuelta se encuentre el propio usuario
	 */
	
	//Esto se tiene que convertir en el metor de listar match debido a la consulta
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Juntos> listarMatch(int id_perfil) {
		String sql="From Perfil where id_perfil in (select id_perfil2 from Juntos where id_perfil1="+id_perfil+")";
		return (List<Juntos>)entityManager.createQuery(sql).getResultList();
		//return (List<Match>)entityManager.createNativeQuery(sql).getResultList();
	}
	
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
		
		List<Contacto> l = this.listarContactos(perfil2.getId());
		for(Contacto c:l) {
			if (c.getId() == perfil1.getId()){
				Juntos juntos=new Juntos();
				juntos.setId_perfil1(perfil1.getId());
				juntos.setId_perfil2(perfil2.getId());
				entityManager.merge(juntos);
			}
		}
	}
	
	@Override
	public void agregarDescarte(Perfil perfil1, Perfil perfil2) {
		Descarte descarte = new Descarte();
		descarte.setId_perfil1(perfil1.getId());
		descarte.setId_perfil2(perfil2.getId());
		entityManager.merge(descarte);
	}
	
}