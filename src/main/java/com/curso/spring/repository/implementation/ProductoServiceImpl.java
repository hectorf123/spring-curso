package com.curso.spring.repository.implementation;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.curso.spring.entity.Producto;
import com.curso.spring.repository.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public EntityManager getEm() {
		return em;
	}

	
	@Override
	public List<Producto> listaTotalProductos(String name) {

		return em.createQuery(
			    "SELECT c FROM Producto c WHERE c.nombre LIKE :custName")
			    .setParameter("custName", name)
			    .getResultList();
		
	}

	@Override
	public Producto insertarProducto() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
