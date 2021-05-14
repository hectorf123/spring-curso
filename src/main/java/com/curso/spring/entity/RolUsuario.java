package com.curso.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "USR_BACKEND", name = "ROLES_USUARIOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolUsuario {
	
	@Id
	@Column(name = "ID_ROL")
	private Long idRol;
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Roles nombre;

	
	
	
}
