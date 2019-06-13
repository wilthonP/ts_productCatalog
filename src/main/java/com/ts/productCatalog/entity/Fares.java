package com.ts.productCatalog.entity;

public class Fares {

	String idFares; //id
	String descripcion; //breve descricpion
	String tipoFares; //
	String tipoTransaccion;
	String valor;
	double precio;

	public Fares(String idFares, String descripcion, String tipoFares, String tipoTransaccion, String valor,
			double precio) {
		super();
		this.idFares = idFares;
		this.descripcion = descripcion;
		this.tipoFares = tipoFares;
		this.tipoTransaccion = tipoTransaccion;
		this.valor = valor;
		this.precio = precio;
	}

	public Fares() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdFares() {
		return idFares;
	}

	public void setIdFares(String idFares) {
		this.idFares = idFares;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoFares() {
		return tipoFares;
	}

	public void setTipoFares(String tipoFares) {
		this.tipoFares = tipoFares;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
