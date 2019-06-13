package com.ts.productCatalog.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.camel.json.simple.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.google.gson.JsonObject;
import com.ts.productCatalog.controller.AutorizacionEventoQuery;
import com.ts.productCatalog.entity.AutorizacionEvento;
import com.ts.productCatalog.entity.MensajesAlerta;
import com.ts.productCatalog.repository.AutorizacionEventoRepository;

@Component
public class AutorizacionEventoComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(AutorizacionEventoComponent.class);
	private AutorizacionEventoRepository autorizacionRepository;
	MensajesAlerta mensajesAlerta;
	Date now = new Date();
	
	@Autowired
	public AutorizacionEventoComponent(AutorizacionEventoRepository autorizacionRepository) {
		super();
		this.autorizacionRepository = autorizacionRepository;
		this.mensajesAlerta = new MensajesAlerta();
	}
	
	public List<AutorizacionEvento> getAutorizacionEventoById(AutorizacionEventoQuery query){
		List<AutorizacionEvento> autorizacion = autorizacionRepository.findByIdAndEstado(query.getId(), query.getEstado());
		List<AutorizacionEvento> autorizacionResult = new ArrayList<AutorizacionEvento>();
		autorizacionResult.addAll(autorizacion);
		return autorizacionResult;
	}
	public List<AutorizacionEvento> getAutorizacionEventoByIdEventoAndEstado(AutorizacionEventoQuery query){
		List<AutorizacionEvento> autorizacion = autorizacionRepository.findByIdEventoAndEstado(query.getIdEvento(), query.getEstado());
		List<AutorizacionEvento> autorizacionResult = new ArrayList<AutorizacionEvento>();
		autorizacionResult.addAll(autorizacion);
		return autorizacionResult;
	}
	public List<AutorizacionEvento> getAutorizacionEventoByIdEvento(AutorizacionEventoQuery query){
		List<AutorizacionEvento> autorizacion = autorizacionRepository.findByIdEvento(query.getIdEvento());
		List<AutorizacionEvento> autorizacionResult = new ArrayList<AutorizacionEvento>();
		autorizacionResult.addAll(autorizacion);
		return autorizacionResult;
	}
	public AutorizacionEvento getAutorizacionEventoByNumAutorizacion(AutorizacionEventoQuery query){
		AutorizacionEvento autorizacion = autorizacionRepository.findByNumAutorizacion(query.getNumAutorizacion());
		return autorizacion;
	}
	public List<AutorizacionEvento> getAutorizacionEventoByEstado(AutorizacionEventoQuery query){
		List<AutorizacionEvento> autorizacion = autorizacionRepository.findByEstado(query.getEstado());
		List<AutorizacionEvento> autorizacionResult = new ArrayList<AutorizacionEvento>();
		autorizacionResult.addAll(autorizacion);
		return autorizacionResult;
	}
	public List<AutorizacionEvento> getAutorizacionEventoAll(){
		List<AutorizacionEvento> autorizacion = autorizacionRepository.findAll();
		List<AutorizacionEvento> autorizacionResult = new ArrayList<AutorizacionEvento>();
		autorizacionResult.addAll(autorizacion);
		return autorizacionResult;
	}
	public MensajesAlerta create(AutorizacionEvento autorizacion){
		try {
			AutorizacionEvento objAutorizacion = autorizacionRepository.findByNumAutorizacionAndIdEvento(autorizacion.getNumAutorizacion(),autorizacion.getIdEvento());
			if(objAutorizacion != null) {
				mensajesAlerta.setCodigo("02");
				mensajesAlerta.setMensaje("La Autorización N° "+autorizacion.getNumAutorizacion()+" ya fue creada o está en proceso de creación.");
			}else {
				autorizacion.setFechaCreacion(now);
				autorizacion.setFechaModificacion(now);
				//autorizacion.setEstado("P");
				if(autorizacionRepository.save(autorizacion) != null) {
					mensajesAlerta.setCodigo("01");
					mensajesAlerta.setParam(autorizacion.getId());
					mensajesAlerta.setMensaje("Datos de Autorizacion del Evento guardados correctamente.");
				}
				else {
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al crear la autorizacion.");
				}
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al crear la autorizacion.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta update(AutorizacionEvento autorizacion){
		try {
			AutorizacionEvento objAutorizacionEvento = autorizacionRepository.findById(autorizacion.getId());
			autorizacion.setUsuarioCreacion(objAutorizacionEvento.getUsuarioCreacion());
			autorizacion.setFechaCreacion(objAutorizacionEvento.getFechaCreacion());
			autorizacion.setInicio(objAutorizacionEvento.getInicio());
			autorizacion.setFin(objAutorizacionEvento.getFin());
			autorizacion.setFechaModificacion(now);
			if(autorizacionRepository.save(autorizacion) != null) {
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Datos de Autorizacion actualizado correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar la autorizacion.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al actualizar la autorizacion.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta setStatus(AutorizacionEventoQuery query){
		try {
			AutorizacionEvento objImp = autorizacionRepository.findById(query.getId());
			objImp.setEstado(query.getEstado());
			objImp.setFechaModificacion(now);
			String estado = ""; 
			if(autorizacionRepository.save(objImp) != null) {
				if(query.getEstado().equals("A"))
					estado = "activado";
				else
					estado = "inactivado";
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Autorizacion "+estado+" correctamente.");
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
	
	public MensajesAlerta deleteAutorizacion(AutorizacionEventoQuery query){
		try { 
			autorizacionRepository.deleteById(query.getId());
			mensajesAlerta.setCodigo("01");
			mensajesAlerta.setMensaje("Autorización eliminada correctamente.");
			
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("problema en el sistema al eliminar la autorización.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
}
