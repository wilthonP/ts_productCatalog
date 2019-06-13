package com.ts.productCatalog.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.google.gson.JsonObject;
//import org.apache.camel.json.simple.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ts.productCatalog.controller.TicketSeguroQuery;
import com.ts.productCatalog.entity.TicketSeguro;
import com.ts.productCatalog.entity.MensajesAlerta;
import com.ts.productCatalog.repository.TicketSeguroRepository;

@Component
public class TicketSeguroComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketSeguroComponent.class);
	private TicketSeguroRepository ticketSeguroRepository;
	MensajesAlerta mensajesAlerta;
	Date now = new Date();
	
	@Autowired
	public TicketSeguroComponent(TicketSeguroRepository ticketSeguroRepository) {
		super();
		this.ticketSeguroRepository = ticketSeguroRepository;
		this.mensajesAlerta = new MensajesAlerta();
	}
	
	public TicketSeguro getTicketSeguroById(TicketSeguroQuery query){
		TicketSeguro ticketSeguro = ticketSeguroRepository.findById(query.getId());
		if(ticketSeguro.equals(null)) 
			ticketSeguro = new TicketSeguro();
		
		return ticketSeguro;
	}
	public List<TicketSeguro> getTicketSeguroByIdEvento(TicketSeguroQuery query){
		List<TicketSeguro> ticketSeguro = ticketSeguroRepository.findByIdEvento(query.getIdEvento());
		List<TicketSeguro> ticketSeguroResult = new ArrayList<TicketSeguro>();
		ticketSeguroResult.addAll(ticketSeguro);
		return ticketSeguroResult;
	}
	public List<TicketSeguro> getTicketSeguroByEstado(TicketSeguroQuery query){
		List<TicketSeguro> ticketSeguro = ticketSeguroRepository.findByEstado(query.getEstado());
		List<TicketSeguro> ticketSeguroResult = new ArrayList<TicketSeguro>();
		ticketSeguroResult.addAll(ticketSeguro);
		return ticketSeguroResult;
	}
	public List<TicketSeguro> getTicketSeguroAll(){
		List<TicketSeguro> ticketSeguro = ticketSeguroRepository.findAll();
		List<TicketSeguro> ticketSeguroResult = new ArrayList<TicketSeguro>();
		ticketSeguroResult.addAll(ticketSeguro);
		return ticketSeguroResult;
	}
	public MensajesAlerta create(TicketSeguro ticketSeguro){
		try {
			ticketSeguro.setFechaCreacion(now);
			ticketSeguro.setFechaModificacion(now);
			if(ticketSeguroRepository.save(ticketSeguro) != null) {
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setParam(ticketSeguro.getId());
				mensajesAlerta.setMensaje("Ticket seguro guardado correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al crear el ticket seguro.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema el ticket seguro.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta update(TicketSeguro ticketSeguro){
		try {
			TicketSeguro objTicketSeguro = ticketSeguroRepository.findById(ticketSeguro.getId());
			ticketSeguro.setUsuarioCreacion(objTicketSeguro.getUsuarioCreacion());
			ticketSeguro.setFechaCreacion(objTicketSeguro.getFechaCreacion());
			ticketSeguro.setFechaModificacion(now);
			if(ticketSeguroRepository.save(ticketSeguro) != null) {
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Ticket seguro actualizado correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar el ticket seguro.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al actualizar el ticket seguro.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta setStatus(TicketSeguroQuery query){
		try {
			TicketSeguro objImp = ticketSeguroRepository.findById(query.getId());
			objImp.setEstado(query.getEstado());
			objImp.setFechaModificacion(now);
			String estado = ""; 
			if(ticketSeguroRepository.save(objImp) != null) {
				if(query.getEstado().equals("A"))
					estado = "activado";
				else
					estado = "inactivado";
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Ticket seguro "+estado+" correctamente.");
			}else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar estado intente nuevamente.");
			}
			return mensajesAlerta;
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("problema en el sistema al actualizar estado.");
			logger.info("Error: " + e);
			return mensajesAlerta;
		}
	}

}
