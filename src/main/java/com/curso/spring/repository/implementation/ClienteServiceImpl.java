package com.curso.spring.repository.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.entity.Cliente;
import com.curso.spring.repository.service.ClienteRepository;

@Service
public class ClienteServiceImpl{
	
	@Autowired
	private ClienteRepository consultasCliente;
	
	public List<Cliente> buscarTodos(){
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		Iterable<Cliente> iterableClientes = consultasCliente.findAll();
		
		iterableClientes.forEach(listaClientes::add);

		return listaClientes;
	}	
	
	/*public Collection<T> buscarTodosA(Iterable<T> iterableClientes){
		
		Collection<T> listaClientes = new ArrayList<T>();
		
		iterableClientes = consultasCliente.findAll();
		
		iterableClientes.forEach(listaClientes::add);
		
		return listaClientes;
		
		
	}*/
}
