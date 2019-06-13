package com.ts.productCatalog.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="frDescuentoProducto")
public class DescuentoProducto {
	@Id
	private String id;
	private String idEvento;
	private String idEscenario;
	private List<EntityGeneric> idLocalidad;
	private String idDescuento;
	private List<DescuentoFpValor> descuentoFormaPago;
	private List<DescuentoCcValor> descuentoCantidadCompra;
	private boolean aplicaAnonimo;
	private Date fechaInicioDecuento;
	private Date fechaFinDecuento;
	private boolean descuentoWeb;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;	
	private String estado;
	
	@Override
	public String toString() {
		return "DescuentoProducto [id=" + id + ", idEvento=" + idEvento + ", idEscenario=" + idEscenario
				+ ", idLocalidad=" + idLocalidad + ", idDescuento=" + idDescuento + ", descuentoFormaPago="
				+ descuentoFormaPago + ", descuentoCantidadCompra=" + descuentoCantidadCompra + ", aplicaAnonimo="
				+ aplicaAnonimo + ", fechaInicioDecuento=" + fechaInicioDecuento + ", fechaFinDecuento="
				+ fechaFinDecuento + ", descuentoWeb=" + descuentoWeb
				+ ", usuarioCreacion=" + usuarioCreacion + ", usuarioModificacion=" + usuarioModificacion
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", estado=" + estado
				+ "]";
	}
	public DescuentoProducto(String id, String idEvento, String idEscenario, List<EntityGeneric> idLocalidad, String idDescuento,
			List<DescuentoFpValor> descuentoFormaPago, List<DescuentoCcValor> descuentoCantidadCompra, boolean aplicaAnonimo,
			Date fechaInicioDecuento, Date fechaFinDecuento, boolean descuentoWeb,
			String usuarioCreacion, String usuarioModificacion, Date fechaCreacion, Date fechaModificacion,
			String estado) {
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
		this.descuentoWeb = descuentoWeb;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estado = estado;
	}
	public DescuentoProducto() {
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
	public List<DescuentoFpValor> getDescuentoFormaPago() {
		return descuentoFormaPago;
	}
	public void setDescuentoFormaPago(List<DescuentoFpValor> descuentoFormaPago) {
		this.descuentoFormaPago = descuentoFormaPago;
	}
	public List<DescuentoCcValor> getDescuentoCantidadCompra() {
		return descuentoCantidadCompra;
	}
	public void setDescuentoCantidadCompra(List<DescuentoCcValor> descuentoCantidadCompra) {
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
	public boolean isDescuentoWeb() {
		return descuentoWeb;
	}
	public void setDescuentoWeb(boolean descuentoWeb) {
		this.descuentoWeb = descuentoWeb;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
