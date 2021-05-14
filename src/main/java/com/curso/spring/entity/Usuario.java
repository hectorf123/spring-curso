package com.curso.spring.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "USR_BACKEND", name = "USUARIOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1519151545879L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	@Column(name = "USUARIO")
	private String usuario;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "CORREO_ELECTRONICO")
	private String correoElectronico;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLES_USUARIOS", 
	joinColumns = @JoinColumn(name= "ID_USUARIO"),
	inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<RolUsuario> roles = new HashSet<RolUsuario>();
	
	
	
}
