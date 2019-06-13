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

import com.ts.productCatalog.controller.PrecioLocalidadQuery;
import com.ts.productCatalog.entity.PrecioLocalidad;
import com.ts.productCatalog.remoteAPI.InventarioComponent;
import com.ts.productCatalog.entity.EgresoInventario;
import com.ts.productCatalog.entity.Evento;
import com.ts.productCatalog.entity.IngresoInventario;
import com.ts.productCatalog.entity.ItemServicioValor;
import com.ts.productCatalog.entity.MensajesAlerta;
import com.ts.productCatalog.repository.EventoRepository;
import com.ts.productCatalog.repository.PrecioLocalidadRepository;

@Component
public class PrecioLocalidadComponent {
	
	@Autowired
	InventarioComponent inventarioComponent;
	
	private static final Logger logger = LoggerFactory.getLogger(PrecioLocalidadComponent.class);
	private PrecioLocalidadRepository precioLocalidadRepository;
	private EventoRepository eventoRepository;
	MensajesAlerta mensajesAlerta;
	Date now = new Date();
	
	
	
	@Autowired
	public PrecioLocalidadComponent(PrecioLocalidadRepository precioLocalidadRepository, EventoRepository eventoRepository) {
		super();
		this.precioLocalidadRepository = precioLocalidadRepository;
		this.eventoRepository = eventoRepository;
		this.mensajesAlerta = new MensajesAlerta();
	}
	
	public List<PrecioLocalidad> getPrecioLocalidadById(PrecioLocalidadQuery query){
		List<PrecioLocalidad> precioLocalidad = precioLocalidadRepository.findByIdAndEstado(query.getId(), query.getEstado());
		List<PrecioLocalidad> precioLocalidadResult = new ArrayList<PrecioLocalidad>();
		precioLocalidadResult.addAll(precioLocalidad);
		return precioLocalidadResult;
	}
	public List<PrecioLocalidad> getPrecioLocalidadByIdEventoAndEstado(PrecioLocalidadQuery query){
		List<PrecioLocalidad> precioLocalidad = precioLocalidadRepository.findByIdEventoAndEstado(query.getIdEvento(), query.getEstado());
		List<PrecioLocalidad> precioLocalidadResult = new ArrayList<PrecioLocalidad>();
		precioLocalidadResult.addAll(precioLocalidad);
		return precioLocalidadResult;
	}
	public List<PrecioLocalidad> getPrecioLocalidadByIdEvento(PrecioLocalidadQuery query){
		List<PrecioLocalidad> precioLocalidad = precioLocalidadRepository.findByIdEvento(query.getIdEvento());
		List<PrecioLocalidad> precioLocalidadResult = new ArrayList<PrecioLocalidad>();
		precioLocalidadResult.addAll(precioLocalidad);
		return precioLocalidadResult;
	}
	public List<PrecioLocalidad> getPrecioLocalidadByEstado(PrecioLocalidadQuery query){
		List<PrecioLocalidad> precioLocalidad = precioLocalidadRepository.findByEstado(query.getEstado());
		List<PrecioLocalidad> precioLocalidadResult = new ArrayList<PrecioLocalidad>();
		precioLocalidadResult.addAll(precioLocalidad);
		return precioLocalidadResult;
	}
	public List<PrecioLocalidad> getPrecioLocalidadAll(){
		List<PrecioLocalidad> precioLocalidad = precioLocalidadRepository.findAll();
		List<PrecioLocalidad> precioLocalidadResult = new ArrayList<PrecioLocalidad>();
		precioLocalidadResult.addAll(precioLocalidad);
		return precioLocalidadResult;
	}
	public MensajesAlerta create(PrecioLocalidad precioLocalidad){
		try {
			precioLocalidad.setFechaCreacion(now);
			precioLocalidad.setFechaModificacion(now);
			if(precioLocalidadRepository.save(precioLocalidad) != null) {
				
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setParam(precioLocalidad.getId());
				mensajesAlerta.setMensaje("Precios del Evento guardados correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al crear los precios.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al crear los precios.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta update(PrecioLocalidad precioLocalidad){
		try {
			PrecioLocalidad objPrecioLocalidad = precioLocalidadRepository.findById(precioLocalidad.getId());
			precioLocalidad.setUsuarioCreacion(objPrecioLocalidad.getUsuarioCreacion());
			precioLocalidad.setFechaCreacion(objPrecioLocalidad.getFechaCreacion());
			precioLocalidad.setFechaModificacion(now);
			if(precioLocalidadRepository.save(precioLocalidad) != null){
				Evento evento = eventoRepository.findById(precioLocalidad.getIdEvento());
				EgresoInventario egreso = new EgresoInventario();
				System.out.println(evento);
				if(evento.getEstado().equals("A")) {
					List<ItemServicioValor> valoresItemServicio = precioLocalidad.getValoresServicios();
					double valorServicio = 0;
					double valorPrecio = 0;
					
					for(ItemServicioValor vis : valoresItemServicio){
					    //Obtiene los valores
						if(vis.getIdItemServicio().equals(PrecioLocalidad.idValorServicio))
							valorServicio = vis.getValor();
						if(vis.getIdItemServicio().equals(PrecioLocalidad.idPrecio))
							valorPrecio = vis.getValor();
					}

					egreso.setPrecio(valorServicio + valorPrecio);
					egreso.setIdLocalidad(precioLocalidad.getIdLocalidad());
					System.out.println("precio"+ egreso.getPrecio());
					Object obj = inventarioComponent.updateInventarioPrecio(egreso);
					System.out.println(obj);
				}
					
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Precios actualizados correctamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar los precios.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al actualizar los precios.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta setStatus(PrecioLocalidadQuery query){
		try {
			PrecioLocalidad objImp = precioLocalidadRepository.findById(query.getId());
			objImp.setEstado(query.getEstado());
			objImp.setFechaModificacion(now);
			String estado = ""; 
			if(precioLocalidadRepository.save(objImp) != null) {
				if(query.getEstado().equals("A"))
					estado = "activados";
				else
					estado = "inactivados";
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Precios "+estado+" correctamente.");
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
