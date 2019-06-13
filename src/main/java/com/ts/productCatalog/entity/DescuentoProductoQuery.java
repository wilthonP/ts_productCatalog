package com.ts.productCatalog.entity;

import java.util.Date;
import java.util.List;

import com.ts.productCatalog.entity.EntityGeneric;

public class DescuentoProductoQuery {
	private String id;
	private String idEvento;
	private String idEscenario;
	private List<EntityGeneric> idLocalidad;
	private String idDescuento;
	private List<String> descuentoFormaPago;
	private List<String> descuentoCantidadCompra;
	private boolean aplicaAnonimo;
	private Date fechaInicioDecuento;
	private Date fechaFinDecuento;
	private boolean aplicarPrecio;
	private boolean descuentoWeb;
	private String estado;
	
	@Override
	public String toString() {
		return "DescuentoProductoQuery [id=" + id + ", idEvento=" + idEvento + ", idEscenario=" + idEscenario
				+ ", idLocalidad=" + idLocalidad + ", idDescuento=" + idDescuento + ", descuentoFormaPago="
				+ descuentoFormaPago + ", descuentoCantidadCompra=" + descuentoCantidadCompra + ", aplicaAnonimo="
				+ aplicaAnonimo + ", fechaInicioDecuento=" + fechaInicioDecuento + ", fechaFinDecuento="
				+ fechaFinDecuento + ", aplicarPrecio=" + aplicarPrecio + ", descuentoWeb=" + descuentoWeb + ", estado="
				+ estado + "]";
	}
	public DescuentoProductoQuery(String id, String idEvento, String idEscenario, List<EntityGeneric> idLocalidad,
			String idDescuento, List<String> descuentoFormaPago, List<String> descuentoCantidadCompra,
			boolean aplicaAnonimo, Date fechaInicioDecuento, Date fechaFinDecuento, boolean aplicarPrecio,
			boolean descuentoWeb, String estado) {
		super();
		this.id = id;
		this.idEvento = idEvento;
		this.idEscenario = idEscenario;
		this.idLocalidad = idLocalidad;
		this.idDescuento = idDescuento;
		this.descuentoFormaPago = descuentoFormaPago;
		this.descuentoCantidadCompra = descuentoCantidadCompra;
		this.aplicaAnonimo = aplicaAnonimo;
		this.fechaInicioDecuento = fechaInicioDecuento;
		this.fechaFinDecuento = fechaFinDecuento;
		this.aplicarPrecio = aplicarPrecio;
		this.descuentoWeb = descuentoWeb;
		this.estado = estado;
	}
	public DescuentoProductoQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<EntityGeneric> getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(List<EntityGeneric> idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getIdDescuento() {
		return idDescuento;
	}
	public void setIdDescuento(String idDescuento) {
		this.idDescuento = idDescuento;
	}
	public List<String> getDescuentoFormaPago() {
		return descuentoFormaPago;
	}
	public void setDescuentoFormaPago(List<String> descuentoFormaPago) {
		this.descuentoFormaPago = descuentoFormaPago;
	}
	public List<String> getDescuentoCantidadCompra() {
		return descuentoCantidadCompra;
	}
	public void setDescuentoCantidadCompra(List<String> descuentoCantidadCompra) {
		this.descuentoCantidadCompra = descuentoCantidadCompra;
	}
	public boolean isAplicaAnonimo() {
		return aplicaAnonimo;
	}
	public void setAplicaAnonimo(boolean aplicaAnonimo) {
		this.aplicaAnonimo = aplicaAnonimo;
	}
	public Date getFechaInicioDecuento() {
		return fechaInicioDecuento;
	}
	public void setFechaInicioDecuento(Date fechaInicioDecuento) {
		this.fechaInicioDecuento = fechaInicioDecuento;
	}
	public Date getFechaFinDecuento() {
		return fechaFinDecuento;
	}
	public void setFechaFinDecuento(Date fechaFinDecuento) {
		this.fechaFinDecuento = fechaFinDecuento;
	}
	public boolean isAplicarPrecio() {
		return aplicarPrecio;
	}
	public void setAplicarPrecio(boolean aplicarPrecio) {
		this.aplicarPrecio = aplicarPrecio;
	}
	public boolean isDescuentoWeb() {
		return descuentoWeb;
	}
	public void setDescuentoWeb(boolean descuentoWeb) {
		this.descuentoWeb = descuentoWeb;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
