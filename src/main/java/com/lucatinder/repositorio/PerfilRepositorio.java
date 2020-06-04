package com.lucatinder.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucatinder.modelo.Perfil;

@Repository("perfilRepositorio")
public interface PerfilRepositorio extends JpaRepository<Perfil, Integer>{
	Perfil findByNombre(String nombre);
	Perfil findByPassword(String password);
}
