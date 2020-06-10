package com.lucatinder.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucatinder.modelo.Rol;


@Repository("rolRepositorio")
public interface RolRepositorio extends JpaRepository<Rol, Integer>{
	Rol findByRol(String rol);
}
