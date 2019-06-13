package com.ts.productCatalog.entity;

import java.util.List;

public class DescuentoLocalidad {
	String idEvento;
	String idLocalidad;
	String nombre;
	String descripcion;
	Double precio;
	List<ListDescuentoLocalidad> descuentosLocalidad;
	
	@Override
	public String toString() {
		return "DescuentoLocalidad [idEvento=" + idEvento + ", idLocalidad=" + idLocalidad + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", precio=" + precio + ", descuentosLocalidad=" + descuentosLocalidad
				+ "]";
	}
	public DescuentoLocalidad(String idEvento, String idLocalidad, String nombre, String descripcion, Double precio,
			List<ListDescuentoLocalidad> descuentosLocalidad) {
		super();
		this.idEvento = idEvento;
		this.idLocalidad = idLocalidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.descuentosLocalidad = descuentosLocalidad;
	}
	public DescuentoLocalidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public String getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
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
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public List<ListDescuentoLocalidad> getDescuentosLocalidad() {
		return descuentosLocalidad;
	}
	public void setDescuentosLocalidad(List<ListDescuentoLocalidad> descuentosLocalidad) {
		this.descuentosLocalidad = descuentosLocalidad;
	}
}
