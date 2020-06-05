package com.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucatinder.control.Control;
import com.lucatinder.datos.PerfilDAO;
import com.lucatinder.servicios.PerfilServicios;

/**
 * Clase que recoge diferentes método de testeo de inyección de dependencias.
 * @author David Vigón, Alejandro Jurado, Ruben Fernández
 * Fecha: 5-6-2020
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAutowired {

	/*
	 * La anotación @Autowired es interpretada por Spring. Las dependencias se
	 * inyectan antes de ejecutar los tests. We are using AssertJ (assertThat()
	 * etc.) to express the test assertions.
	 */

	@Autowired
	private Control control;

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PerfilServicios perfilServicios;

	/*
	 * Si el resultado al ejecutar el JUnit es verde, indica que todas las
	 * dependencias han sido inyectadas correctamente. En caso contrario, alguna de
	 * las dependencias ha fallado. Para provocar el fallo, se puede eliminar alguna
	 * de las anotaciones de tipo @Component.
	 */
	@Test
	public void contexLoads() throws Exception {
		assertThat(control).isNotNull();
		assertThat(perfilDAO).isNotNull();
		assertThat(dataSource).isNotNull();
		assertThat(perfilServicios).isNotNull();
	}

}
