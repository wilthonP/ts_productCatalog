package com.ts.productCatalog.entity;

import java.util.ArrayList;

public class LocalidadPedido {

	private String idEvento;
	private String idLocalidad;
	private String nombreLocalidades;
	private String descripcionLocalidades;
	private Double precio;
	private Double precioTotal;
	private ArrayList<Fares> fares;
	
	public LocalidadPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalidadPedido(String idEvento, String idLocalidad, Integer cantidad, Double precio, Double precioTotal,
			ArrayList<Fares> fares) {
		super();
		this.idEvento = idEvento;
		this.idLocalidad = idLocalidad;
		this.precio = precio;
		this.precioTotal = precioTotal;
		this.fares = fares;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public ArrayList<Fares> getFares() {
		return fares;
	}

	public void setFares(ArrayList<Fares> fares) {
		this.fares = fares;
	}

	public String getNombreLocalidades() {
		return nombreLocalidades;
	}

	public void setNombreLocalidades(String nombreLocalidades) {
		this.nombreLocalidades = nombreLocalidades;
	}

	public String getDescripcionLocalidades() {
		return descripcionLocalidades;
	}

	public void setDescripcionLocalidades(String descripcionLocalidades) {
		this.descripcionLocalidades = descripcionLocalidades;
	}
}
