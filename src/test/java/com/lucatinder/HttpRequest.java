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
/**
 * Clase para comprobar la existencia 
 * @author admin
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
     * Método que comprueba la existencia de un determinado contenido en la página
     * de registro
     */
    @Test
    public void devuelveRegistro() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/registro",String.class)).
          contains("registro");
    }
    
    /*
     * Método que comprueba la existencia de un determinado contenido en la página
     * de inicio
     */
    @Test
    public void devuelveInicio() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/inicio",String.class)).
          contains("inicio");
    }
    
    /*
     * Método que comprueba la existencia de un determinado contenido en la página
     * de login
     */
    @Test
    public void devuelveLogin() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/login",String.class)).
          contains("login");
    }
    
    /*
     * Método que comprueba la existencia de un determinado contenido en la página
     * listado de perfiles
     */
    @Test
    public void devuelveListadoPerfiles() throws Exception {
        assertThat(this.restTemplate.
        		getForObject("http://localhost:" + port + "/listado",String.class)).
          contains("listado de perfiles");
    }
    
}
