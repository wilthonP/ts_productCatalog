package com.ts.productCatalog.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pcImpuestosEvento")
public class ImpuestoEvento {
	@Id
	private String id;
	private String idImpuesto; //id de fares
	private String idEvento;
	private TipoImpuesto tipoImpuesto;
	private double valor;
	
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;	
	private String estado;
	
	@Override
	public String toString() {
		return "ImpuestoEvento [id=" + id + ", idImpuesto=" + idImpuesto + ", idEvento=" + idEvento + ", tipoImpuesto="
				+ tipoImpuesto + ", valor=" + valor + ", usuarioCreacion=" + usuarioCreacion + ", usuarioModificacion="
				+ usuarioModificacion + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion
				+ ", estado=" + estado + "]";
	}
	public ImpuestoEvento(String id, String idImpuesto, String idEvento, TipoImpuesto tipoImpuesto, double valor,
			String usuarioCreacion, String usuarioModificacion, Date fechaCreacion, Date fechaModificacion,
			String estado) {
		super();
		this.id = id;
		this.idImpuesto = idImpuesto;
		this.idEvento = idEvento;
		this.tipoImpuesto = tipoImpuesto;
		this.valor = valor;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.estado = estado;
	}
	public ImpuestoEvento() {
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
