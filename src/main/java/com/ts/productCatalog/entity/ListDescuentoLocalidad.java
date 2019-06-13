package com.ts.productCatalog.entity;

import java.util.List;

public class ListDescuentoLocalidad {
	String idDescuento;
	String nombre;
	List<DescuentoFpValor> descuentoFormaPago;
	
	@Override
	public String toString() {
		return "ListDescuentoLocalidad [idDescuento=" + idDescuento + ", nombre=" + nombre + ", descuentoFormaPago="
				+ descuentoFormaPago + "]";
	}
	public ListDescuentoLocalidad(String idDescuento, String nombre, List<DescuentoFpValor> descuentoFormaPago) {
		super();
		this.idDescuento = idDescuento;
		this.nombre = nombre;
		this.descuentoFormaPago = descuentoFormaPago;
	}
	public ListDescuentoLocalidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIdDescuento() {
		return idDescuento;
	}
	public void setIdDescuento(String idDescuento) {
		this.idDescuento = idDescuento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<DescuentoFpValor> getDescuentoFormaPago() {
		return descuentoFormaPago;
	}
	public void setDescuentoFormaPago(List<DescuentoFpValor> descuentoFormaPago) {
		this.descuentoFormaPago = descuentoFormaPago;
	}
}
