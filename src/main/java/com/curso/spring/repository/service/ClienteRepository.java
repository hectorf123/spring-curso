package com.curso.spring.repository.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
}
