package com.curso.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.payload.request.UsuarioRequest;
import com.curso.spring.repository.service.UsuarioRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/registrar-usuario", method = RequestMethod.POST)
	public ResponseEntity<?> insertarUsuario(UsuarioRequest usuarioRequest) {

		return null;

	}

	
}
