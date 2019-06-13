package com.ts.productCatalog.controller;

import java.util.Date;

public class TicketSeguroQuery {
	private String id;
	private String idSeguro;
	private double valor;
	private String idEvento;
	private String idEscenario;
	private String idLocalidad;
	private String estado;
	
	public TicketSeguroQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(String idSeguro) {
		this.idSeguro = idSeguro;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public String getIdEscenario() {
		return idEscenario;
	}
	public void setIdEscenario(String idEscenario) {
		this.idEscenario = idEscenario;
	}
	public String getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
