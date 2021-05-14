package com.curso.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.Usuario;
import com.curso.spring.payload.request.LoginRequest;
import com.curso.spring.payload.response.ResponseCodes;
import com.curso.spring.repository.service.UsuarioRepository;
import com.curso.spring.security.jwt.JwtUtil;
import com.curso.spring.security.services.UserJWTService;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("USUARIO" + loginRequest.getContrasena());
		System.out.println("USUARIO" + loginRequest.getUsuario());
		Usuario usuario = usuarioRepository.consultarUsuario(loginRequest.getUsuario());
		String contrasenaBaseDatos = usuario.getPassword();
		Boolean contrasenaCoindice;

		
		System.out.println("CAmbio prueba repositorio");
		System.out.println(usuario.getCorreoElectronico());
		if (usuario.getUsuario() == null) {
			return ResponseEntity.ok(new ResponseCodes(1, "Usuario  incorrecta"));
		}
		if (usuario != null) {
			Authentication autenticacion = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getContrasena()));

			System.out.println("Security" + autenticacion.getPrincipal());

			SecurityContextHolder.getContext().setAuthentication(autenticacion);
			String jwt = jwtUtil.generarTokenApp(autenticacion);
			UserJWTService userDetails = (UserJWTService) autenticacion.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			
			//1. Retornen objeto que contenga al usuario(Rol)
			//2. Validen la contraseña en método authenticateUser(opcional)
			//3. Registren usuario usando encoder como método de encriptación
			
			
			return ResponseEntity.ok(jwt);

		} else {
			return ResponseEntity
					.ok(new ResponseCodes(3, "Ocurrió un error inesperado, comuníquese con el administrador"));
		}

	}

}
