package com.ts.productCatalog.entity;

import java.util.ArrayList;
import java.util.Date;

import com.ts.productCatalog.entity.DatosFacturacion;
import com.ts.productCatalog.entity.Fares;
import com.ts.productCatalog.entity.LocalidadPedido;

public class Pedido {
	
	private String id;
	private String idUsuario;
	//evento
	private String idEvento;
	private String nombreEvento; 
	private String descripcionEvento;
	private String idFormaPago;
	private boolean pedidoShopping;
	private String codigo;
	private double subTotal;
	private double total;
	//datos factura
	private DatosFacturacion datosFacturacion;
	//tiempo reserva
	private Date  tiempoReserva;
	//datos fares
	private ArrayList<Fares> fares;
	//localidades
	private ArrayList<LocalidadPedido> localidades;
	//entrega
	private boolean domicilio;
	//private DatosPersonales datosPersonaRecibe;
	private String estado;
	//Auditoria
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Date  fechaCreacion;
	private Date  fechaModificacion;

	public Pedido(String id, String idUsuario, String idEvento, String nombreEvento, String descripcionEvento,
			String idFormaPago, boolean pedidoShopping, String codigo, double subTotal, double total,
			DatosFacturacion datosFacturacion, Date tiempoReserva, ArrayList<Fares> fares,
			ArrayList<LocalidadPedido> localidades, boolean domicilio, String estado, String usuarioCreacion,
			String usuarioModificacion, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idEvento = idEvento;
		this.nombreEvento = nombreEvento;
		this.descripcionEvento = descripcionEvento;
		this.idFormaPago = idFormaPago;
		this.pedidoShopping = pedidoShopping;
		this.codigo = codigo;
		this.subTotal = subTotal;
		this.total = total;
		this.datosFacturacion = datosFacturacion;
		this.tiempoReserva = tiempoReserva;
		this.fares = fares;
		this.localidades = localidades;
		this.domicilio = domicilio;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DatosFacturacion getDatosFacturacion() {
		return datosFacturacion;
	}

	public void setDatosFacturacion(DatosFacturacion datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(String idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	
	public boolean isPedidoShopping() {
		return pedidoShopping;
	}

	public void setPedidoShopping(boolean pedidoShopping) {
		this.pedidoShopping = pedidoShopping;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getTiempoReserva() {
		return tiempoReserva;
	}

	public void setTiempoReserva(Date tiempoReserva) {
		this.tiempoReserva = tiempoReserva;
	}

	public ArrayList<Fares> getFares() {
		return fares;
	}

	public boolean isDomicilio() {
		return domicilio;
	}

	public void setDomicilio(boolean domicilio) {
		this.domicilio = domicilio;
	}

	public void setFares(ArrayList<Fares> fares) {
		this.fares = fares;
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

	public ArrayList<LocalidadPedido> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(ArrayList<LocalidadPedido> localidades) {
		this.localidades = localidades;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescripcionEvento() {
		return descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
}
