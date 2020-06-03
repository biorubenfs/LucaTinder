package com.lucatinder.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Configuracion extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Value("${select  nombre, password from perfil where nombre=?}")
	private String perfilQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery(perfilQuery)
			.authoritiesByUsernameQuery(perfilQuery)
			.dataSource(dataSource);
			//No se esta usando realmente porque lo genero desde Servicios
			//.passwordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/").permitAll()		
			.antMatchers("/login").permitAll()
			.antMatchers("/listado").permitAll()
			.antMatchers("/registrar").permitAll()
			.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
				.formLogin().loginPage("/login")
					//.defaultSuccessUrl("/admin")
					.failureUrl("/login?error=true")
					.usernameParameter("nombre")
					.passwordParameter("password")
				.and()
			.csrf()
				.disable()
				.headers().frameOptions().disable()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
