package com.curso.spring.security.services;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.curso.spring.entity.Usuario;

import lombok.Data;
@Data
public class UserJWTService implements UserDetails{

	private static final long serialVersionUID = 1594848748L;

	private Long id;
	private String usuario;
	private String password;
	private String correoElectronico;
	
	private Collection<? extends GrantedAuthority> permisos;
	
	
	public UserJWTService() {
		
	}
	
	public UserJWTService(Long id, String usuario, String password, String correoElectronico,
			Collection<? extends GrantedAuthority> permisos) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.correoElectronico = correoElectronico;
		this.permisos = permisos;
	}
	
	public UserJWTService construirObjetoUsuario(Usuario usuario) {
		//Calculo Lambda y Streams en java
		List<GrantedAuthority> autoridades = usuario.getRoles().stream()
				.map(role-> new SimpleGrantedAuthority(role.getNombre().name()))
				.collect(Collectors.toList());
		
		return new UserJWTService(usuario.getIdUsuario(), 
				usuario.getUsuario(), usuario.getPassword(), usuario.getCorreoElectronico(), 
				autoridades);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permisos;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
