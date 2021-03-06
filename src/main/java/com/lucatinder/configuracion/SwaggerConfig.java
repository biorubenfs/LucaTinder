package com.lucatinder.configuracion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.lucatinder.control.Control;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * http://localhost:8080/v2/api-docs
 * http://localhost:8080/swagger-ui.html
 * @author Rubén Fernández
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig /*extends WebMvcConfigurationSupport*/{
	
	private static final Logger logger = LoggerFactory.getLogger(Control.class);

	@Bean
    public Docket api() { 
		logger.info(">>>>>>>>>>> dentro de Swagger");
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();
                                              
    }
	
	/*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/
}
