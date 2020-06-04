package com.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequest {
	
	/*
	 * we could start the application up and listen for a 
	 * connection like it would do in production, and 
	 * then send an HTTP request and assert the response.
	 * 
	 * Note the use of webEnvironment=RANDOM_PORT to start the server with a random port 
	 * (useful to avoid conflicts in test environments), 
	 * and the injection of the port with  @LocalServerPort. 
	 * Also note that Spring Boot has provided a TestRestTemplate 
	 * for you automatically, and all you have to do is @Autowired it.
	 */

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void devuelveRegistro() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/registro",String.class)).
          contains("registro");
    }
    
    /* Comentado porque aun no existe la página inicio.html
    @Test
    public void devuelveInicio() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/inicio",String.class)).
          contains("inicio");
    }
    */
    
    @Test
    public void devuelveLogin() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/login",String.class)).
          contains("login");
    }
    
    
}
