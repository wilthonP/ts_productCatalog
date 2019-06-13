package com.ts.productCatalog.entity;

public class DescuentoCcValor {
	private String idDescuento;
	Integer descVendidos;
	double valor;
	
	public String getIdDescuento() {
		return idDescuento;
	}
	public void setIdDescuento(String idDescuento) {
		this.idDescuento = idDescuento;
	}
	public Integer getDescVendidos() {
		return descVendidos;
	}
	public void setDescVendidos(Integer descVendidos) {
		this.descVendidos = descVendidos;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
