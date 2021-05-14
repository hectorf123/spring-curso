package com.curso.spring.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.spring.entity.Usuario;
import com.curso.spring.repository.implementation.UsuarioServiceImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired	
	UsuarioServiceImpl userServiceImpll;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userServiceImpll.consultarUsuario(username);
		UserJWTService u = new UserJWTService();
		
		return u.construirObjetoUsuario(user);
	}
	
	
	
	

}
