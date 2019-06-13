package com.ts.productCatalog.entity;

public class DatosFacturacion {

	String tipoIdentificacion;
	String identificacion;
	String nombreFactura;
	String direccion;
	String ciudad;
	String pais;
	
	public DatosFacturacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosFacturacion(String tipoIdentificacion, String identificacion, String nombreFactura, String direccion,
			String ciudad, String pais) {
		super();
		this.tipoIdentificacion = tipoIdentificacion;
		this.identificacion = identificacion;
		this.nombreFactura = nombreFactura;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
