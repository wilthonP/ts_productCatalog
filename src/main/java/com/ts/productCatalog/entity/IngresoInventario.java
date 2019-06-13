package com.ts.productCatalog.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="invIngresoInventario")
public class IngresoInventario {

	@Id
	private String id;
	private String idTipoIngreso;
	private String idTransaccion;
	private String idLocalidad;
	private Integer cantidad;
	private Double precio;
	private String estado;
	//Auditoria
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date  fechaCreacion;
	private Date  fechaModificacion;
	
	public IngresoInventario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngresoInventario(String idTipoIngreso, String idTransaccion, String idLocalidad, Integer cantidad,
			String estado, String usuarioCreacion, String usuarioModificacion, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.idTipoIngreso = idTipoIngreso;
		this.idTransaccion = idTransaccion;
		this.idLocalidad = idLocalidad;
		this.cantidad = cantidad;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}
	
	

	public IngresoInventario(String idTipoIngreso, String idTransaccion, String idLocalidad, Integer cantidad,
			Double precio, String estado, String usuarioCreacion, String usuarioModificacion, Date fechaCreacion,
			Date fechaModificacion) {
		super();
		this.idTipoIngreso = idTipoIngreso;
		this.idTransaccion = idTransaccion;
		this.idLocalidad = idLocalidad;
		this.cantidad = cantidad;
		this.precio = precio;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdTipoIngreso() {
		return idTipoIngreso;
	}

	public void setIdTipoIngreso(String idTipoIngreso) {
		this.idTipoIngreso = idTipoIngreso;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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


}
