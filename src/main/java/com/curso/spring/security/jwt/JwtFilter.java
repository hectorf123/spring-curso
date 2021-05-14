package com.curso.spring.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.curso.spring.security.services.UserDetailsServiceImpl;

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest peticion, HttpServletResponse respuesta, FilterChain filtro)
			throws ServletException, IOException {

		System.out.println("Hola mundo");
		try {
			String jwt = parseJWT(peticion);
			if (jwtUtil.validarConstruccionToken(jwt, peticion)) {
				String username = jwtUtil.getUserNameFromJWT(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken autenticacion = new UsernamePasswordAuthenticationToken(userDetails,
						userDetails.getAuthorities());

				autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(peticion));

				SecurityContextHolder.getContext().setAuthentication(autenticacion);

			}
		} catch (Exception e) {
			System.out.println("ERROR FILTER" + e.getMessage());
		}

		filtro.doFilter(peticion, respuesta);
	}

	private String parseJWT(HttpServletRequest peticion) {
		String cabecera = peticion.getHeader("Authorization");
		String token = "";
		
		System.out.println(peticion.getHeader("Authorization"));

		if (StringUtils.hasText(cabecera) && cabecera.startsWith("Bearer")) {
			token = cabecera.substring(7, cabecera.length());
		} else {
			return null;
		}

		return token;
	}

}
