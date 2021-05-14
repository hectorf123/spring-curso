package com.curso.spring.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.curso.spring.security.services.UserJWTService;

import io.jsonwebtoken.*;

@Component
public class JwtUtil {

	private String jwt = "springsecretkey";

	public String generarTokenApp(Authentication autenticacion) {
	
		UserJWTService userPrincipal = (UserJWTService) autenticacion.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 600000)) //9:09 + 5
				.signWith(SignatureAlgorithm.HS512, jwt)
				.compact();
	}

	public boolean validarConstruccionToken(String token, HttpServletRequest peticion) {
		try {
			Jwts.parser().setSigningKey(jwt).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT no es soportado");
		}

		return false;
	}
	
	public String getUserNameFromJWT(String token){
		return Jwts.parser().setSigningKey(jwt).parseClaimsJws(token).getBody().getSubject();
	}

}
