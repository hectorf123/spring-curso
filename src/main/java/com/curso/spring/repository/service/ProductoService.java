package com.curso.spring.repository.service;

import java.util.List;

import com.curso.spring.entity.Producto;

public interface ProductoService {

	public List<Producto> listaTotalProductos(String name);
	
	public Producto insertarProducto();
	
}
