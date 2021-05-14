package com.curso.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "USR_BACKEND", name = "PRODUCTOS")
public class Producto implements Serializable {

	private static final long serialVersionUID = 151915151947822L;

	@Id
	@SequenceGenerator(name = "PRODUCTOS_SEQ", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PRODUCTOS_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_PRODUCT")
	private Long idMiProducto;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "PRECIO")
	private Integer precio;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public Producto() {
		super();
	}

	public Long getIdMiProducto() {
		return idMiProducto;
	}

	public void setIdMiProducto(Long idMiProducto) {
		this.idMiProducto = idMiProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
