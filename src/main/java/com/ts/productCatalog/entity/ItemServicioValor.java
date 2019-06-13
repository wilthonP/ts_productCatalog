package com.ts.productCatalog.entity;

public class ItemServicioValor {
	
	String idItemServicio;
	String identificador;
	double valor;
	
	@Override
	public String toString() {
		return "ItemServicioValor [idItemServicio=" + idItemServicio + ", identificador=" + identificador + ", valor="
				+ valor + "]";
	}
	public ItemServicioValor(String idItemServicio, String identificador, double valor) {
		super();
		this.idItemServicio = idItemServicio;
		this.identificador = identificador;
		this.valor = valor;
	}
	public ItemServicioValor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdItemServicio() {
		return idItemServicio;
	}
	public void setIdItemServicio(String idItemServicio) {
		this.idItemServicio = idItemServicio;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}	
}
