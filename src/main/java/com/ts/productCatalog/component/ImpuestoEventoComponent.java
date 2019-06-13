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
import com.ts.productCatalog.controller.ImpuestoEventoQuery;
import com.ts.productCatalog.entity.ImpuestoEvento;
import com.ts.productCatalog.entity.MensajesAlerta;
import com.ts.productCatalog.repository.ImpuestoEventoRepository;

@Component
public class ImpuestoEventoComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(ImpuestoEventoComponent.class);
	private ImpuestoEventoRepository impuestoRepository;
	MensajesAlerta mensajesAlerta;
	Date now = new Date();
	
	@Autowired
	public ImpuestoEventoComponent(ImpuestoEventoRepository impuestoRepository) {
		super();
		this.impuestoRepository = impuestoRepository;
		this.mensajesAlerta = new MensajesAlerta();
	}
	
	public ImpuestoEvento getImpuestoEventoById(ImpuestoEventoQuery query){
		ImpuestoEvento impuesto = impuestoRepository.findById(query.getId());
		if(impuesto.equals(null))
			impuesto = new ImpuestoEvento();
		return impuesto;
	}
	public List<ImpuestoEvento> getImpuestoEventoByIdEvento(ImpuestoEventoQuery query){
		List<ImpuestoEvento> impuesto = impuestoRepository.findByIdEvento(query.getIdEvento());
		List<ImpuestoEvento> impuestoResult = new ArrayList<ImpuestoEvento>();
		impuestoResult.addAll(impuesto);
		return impuestoResult;
	}
	public List<ImpuestoEvento> getImpuestoEventoByEstado(ImpuestoEventoQuery query){
		List<ImpuestoEvento> impuesto = impuestoRepository.findByEstado(query.getEstado());
		List<ImpuestoEvento> impuestoResult = new ArrayList<ImpuestoEvento>();
		impuestoResult.addAll(impuesto);
		return impuestoResult;
	}
	public List<ImpuestoEvento> getImpuestoEventoAll(){
		List<ImpuestoEvento> impuesto = impuestoRepository.findAll();
		List<ImpuestoEvento> impuestoResult = new ArrayList<ImpuestoEvento>();
		impuestoResult.addAll(impuesto);
		return impuestoResult;
	}
	public MensajesAlerta create(ImpuestoEvento impuesto){
		try {
			impuesto.setFechaCreacion(now);
			impuesto.setFechaModificacion(now);
			//impuesto.setEstado("P");
			if(impuestoRepository.save(impuesto) != null) {
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setParam(impuesto.getId());
				mensajesAlerta.setMensaje("Datos de Impuesto del Evento guardados correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al crear la impuesto.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al crear la impuesto.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta update(ImpuestoEvento impuesto){
		try {
			ImpuestoEvento objImpuestoEvento = impuestoRepository.findById(impuesto.getId());
			impuesto.setUsuarioCreacion(objImpuestoEvento.getUsuarioCreacion());
			impuesto.setFechaCreacion(objImpuestoEvento.getFechaCreacion());
			impuesto.setFechaModificacion(now);
			if(impuestoRepository.save(impuesto) != null) {
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Datos de Impuesto actualizado correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar la impuesto.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al actualizar la impuesto.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta setStatus(ImpuestoEventoQuery query){
		try {
			ImpuestoEvento objImp = impuestoRepository.findById(query.getId());
			objImp.setEstado(query.getEstado());
			objImp.setFechaModificacion(now);
			String estado = ""; 
			if(impuestoRepository.save(objImp) != null) {
				if(query.getEstado().equals("A"))
					estado = "activado";
				else
					estado = "inactivado";
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Impuesto "+estado+" correctamente.");
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
	
	public MensajesAlerta deleteImpuesto(ImpuestoEventoQuery query){
		try { 
			impuestoRepository.deleteById(query.getId());
			mensajesAlerta.setCodigo("01");
			mensajesAlerta.setMensaje("Impuesto eliminada correctamente.");
			
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("problema en el sistema al eliminar la impuesto.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
}
