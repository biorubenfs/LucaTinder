package com.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Una prueba estupida
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LucaTinderApplicationTests {
	
	// Esta prueba debería fallar
	@Test
	void contextLoads() {
		assertThat(false).isTrue();
	}
	
	
	/*
	//Esta prueba no debería fallar
	@Test
	void contextLoads() {
		assertThat(true).isTrue();
	}
	*/
    
}


