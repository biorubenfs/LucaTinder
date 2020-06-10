package com.lucatinder.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucatinder.modelo.Perfil;
import com.lucatinder.modelo.Rol;

@Service
public class DetallesPerfilServicios implements UserDetailsService{

	@Autowired
	private PerfilServiciosImp perfil;
	
	@Override
    @Transactional
    public UserDetails loadUserByUsername(String perfilNombre) throws UsernameNotFoundException {
        Perfil per = perfil.findByNombre(perfilNombre);
        List<GrantedAuthority> authorities = getUserAuthority(per.getRol());
        return buildUserForAuthentication(per, authorities);
    }
	
	 private List<GrantedAuthority> getUserAuthority(Set<Rol> perRoles) {
	        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
	        for (Rol role : perRoles) {
	            roles.add(new SimpleGrantedAuthority(role.getRol()));
	        }
	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	        return grantedAuthorities;
	    }

	    private UserDetails buildUserForAuthentication(Perfil per, List<GrantedAuthority> authorities) {
	        return new org.springframework.security.core.userdetails.User(per.getNombre(), per.getPassword(),
	                per.isEnabled(), true, true, true, authorities);
	    }
}
