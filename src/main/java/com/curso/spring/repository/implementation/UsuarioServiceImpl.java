package com.curso.spring.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.curso.spring.entity.Usuario;
import com.curso.spring.repository.service.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioRepository {

	private EntityManager em = null;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	@Override
	public Usuario consultarUsuario(String username) {
		Usuario usuario = null;
		try {
			Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :user", Usuario.class);
			consulta.setParameter("user", username);
			usuario = (Usuario) consulta.getSingleResult();
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		return usuario;
	}

}
