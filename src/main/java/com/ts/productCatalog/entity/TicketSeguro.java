package com.ts.productCatalog.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pcEmpresario")
public class TicketSeguro {
	@Id
	private String id;
	private String idSeguro;
	private double valor;
	private String idEvento;
	private String idEscenario;
	private String idLocalidad;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;	
	private String estado;
	
	@Override
	public String toString() {
		return "TicketSeguro [id=" + id + ", idSeguro=" + idSeguro + ", valor=" + valor + ", idEvento=" + idEvento
				+ ", idEscenario=" + idEscenario + ", idLocalidad=" + idLocalidad + ", usuarioCreacion="
				+ usuarioCreacion + ", usuarioModificacion=" + usuarioModificacion + ", fechaCreacion=" + fechaCreacion
				+ ", fechaModificacion=" + fechaModificacion + ", estado=" + estado + "]";
	}
	public TicketSeguro(String id, String idSeguro, double valor, String idEvento, String idEscenario,
			String idLocalidad, String usuarioCreacion, String usuarioModificacion, Date fechaCreacion,
			Date fechaModificacion, String estado) {
		super();
		this.id = id;
		this.idSeguro = idSeguro;
		this.valor = valor;
		this.idEvento = idEvento;
		this.idEscenario = idEscenario;
		this.idLocalidad = idLocalidad;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estado = estado;
	}
	public TicketSeguro() {
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
