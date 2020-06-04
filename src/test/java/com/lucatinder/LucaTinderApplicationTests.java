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



// Una prueba estupida
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LucaTinderApplicationTests {
	
	@Test
	void contextLoads() {
		assertThat(false).isTrue();
	}
    
}


