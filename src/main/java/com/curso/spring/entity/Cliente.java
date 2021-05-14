package com.curso.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "USR_BACKEND", name = "CLIENTES")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 81918795091961131L;

	@Id
	@SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "CLIENTES_SEQ", strategy = GenerationType.SEQUENCE)
	private Long idCliente;
	
	@Column(name = "NOMBRE")
	private String nombrePrueba;
	
	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name = "TELEFONO")
	private Integer telefono;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name= "ID_PRODUCTO", nullable = false)
	private Producto producto;*/

	public Cliente() {
		super();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombrePrueba() {
		return nombrePrueba;
	}

	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombrePrueba=" + nombrePrueba + ", apellido=" + apellido
				+ ", telefono=" + telefono + ", direccion=" + direccion + "]";
	}
	
}
