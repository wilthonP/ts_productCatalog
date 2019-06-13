package com.ts.productCatalog.entity;

public class ResponseMsg {
	String error;
	String message;
	String status;

	

	@Override
	public String toString() {
		return "ResponseMsg [error=" + error + ", message=" + message + ", status=" + status + "]";
	}
	
	public ResponseMsg(String error, String message, String status) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
	}

	public ResponseMsg(String resp) {
		super();
		switch (resp) {
		case "error": this.error = "00";
					  this.status = "00";
					  this.message = "Transacción Negada";
    			      break;
    			      
		case "exito": this.error = "01";
					  this.status = "01";
		  			  this.message = "Transacción Aceptada";
		 			  break;
		 			  
		case "existsData": this.error = "02";
						   this.status = "00";
					   	   this.message = "El dato a ingresar ya existe";
						   break;
		 			  
		default: this.error = "00";
		         this.status = "01";
				 this.message = "Mensaje desconocido";
		         break;
		}
		
	}

	public ResponseMsg() {
		// TODO Auto-generated constructor stub
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDatos(String datos) {
		this.error="01";
		this.status="02";
		this.message=datos;
	}

}
