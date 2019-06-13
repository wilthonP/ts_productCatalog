package com.ts.productCatalog.controller;

import com.ts.productCatalog.entity.TipoImpuesto;

public class ImpuestoEventoQuery {

	private String id;
	private String idImpuesto;
	private String idEvento;
	private TipoImpuesto tipoImpuesto;
	private double valor;
	private String estado;
	
	public ImpuestoEventoQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdImpuesto() {
		return idImpuesto;
	}
	public void setIdImpuesto(String idImpuesto) {
		this.idImpuesto = idImpuesto;
	}
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}
	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
