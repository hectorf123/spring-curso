package com.curso.spring.repository.service;

import com.curso.spring.entity.Usuario;

public interface UsuarioRepository {

	public Usuario consultarUsuario(String username);
	
}
