package com.ts.productCatalog.component;

import java.util.*;

//import org.apache.camel.json.simple.JsonObject;
import com.ts.productCatalog.entity.*;
import com.ts.productCatalog.remoteAPI.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ts.productCatalog.controller.EventoQuery;
import com.ts.productCatalog.remoteAPI.FaresComponent;
import com.ts.productCatalog.remoteAPI.InventarioComponent;
import com.ts.productCatalog.repository.EventoRepository;
import com.ts.productCatalog.repository.ImpuestoEventoRepository;
import com.ts.productCatalog.repository.LocalidadRepository;
import com.ts.productCatalog.repository.PrecioLocalidadRepository;

import com.ts.productCatalog.services.EventSearchService;

@Component
public class EventoComponent {
	
	@Autowired
	InventarioComponent inventarioComponent;
	@Autowired
	EventSearchService eventSearchService;
	@Autowired
	FaresComponent faresComponent;

	private static final Logger logger = LoggerFactory.getLogger(EventoComponent.class);
	private EventoRepository eventoRepository;
	private ImpuestoEventoRepository impuestoRepository;
	private PrecioLocalidadRepository preciosRepository;
	private LocalidadRepository localidadRepository;
	MensajesAlerta mensajesAlerta;
	Date now = new Date();



	
	@Autowired
	public EventoComponent(EventoRepository eventoRepository, PrecioLocalidadRepository preciosRepository,LocalidadRepository localidadRepository,
							ImpuestoEventoRepository impuestoRepository) {
		super();
		this.eventoRepository = eventoRepository;
		this.preciosRepository = preciosRepository;
		this.localidadRepository = localidadRepository;
		this.impuestoRepository = impuestoRepository;
		this.mensajesAlerta = new MensajesAlerta();
	}
	
	public List<Evento> getEventoById(EventoQuery query){
		List<Evento> evento = eventoRepository.findByIdAndEstado(query.getId(), query.getEstado());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByNombre(EventoQuery query){
		List<Evento> evento = eventoRepository.findByNombreAndEstado(query.getNombre(), query.getEstado());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByEstado(EventoQuery query){
		List<Evento> evento = eventoRepository.findByEstado(query.getEstado());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByCiudad(EventoQuery query){
		List<Evento> evento = eventoRepository.findByCiudad(query.getCiudad());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByEventoNacional(EventoQuery query){
		List<Evento> evento = eventoRepository.findByEventoNacional(query.isEventoNacional());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByAplicaPreventa(EventoQuery query){
		List<Evento> evento = eventoRepository.findByAplicaPreventa(query.isAplicaPreventa());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoByTipoActividad(EventoQuery query){
		List<Evento> evento = eventoRepository.findByTipoActividad(query.getTipoActividad());
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public List<Evento> getEventoAll(){
		List<Evento> evento = eventoRepository.findAllByOrderByNombre();
		List<Evento> eventoResult = new ArrayList<Evento>();
		eventoResult.addAll(evento);
		return eventoResult;
	}
	public MensajesAlerta create(Evento evento){
		try {
			evento.setId(null);
			evento.setFechaCreacion(now);
			evento.setFechaModificacion(now);
			evento.setFechaInicioCanje(null);
			evento.setFechaInicioNormal(null);
			evento.setFechaInicioPreventa(null);
			evento.setFechaFinCanje(null);
			evento.setFechaFinNormal(null);
			evento.setFechaFinPreventa(null);
			if(evento.getEstado() != "P") {
				if(evento.getModoVenta() == "C")
					evento.setFechaInicioCanje(now);
				else if(evento.getModoVenta() == "N")
					evento.setFechaInicioNormal(now);
				else
					evento.setFechaInicioPreventa(now);
			}



			if(eventoRepository.save(evento) != null) {


				if(evento.isPublicado() && "A".equalsIgnoreCase(evento.getEstado())){
					EventSearched eventSearched = eventSearchService.getSearchEvent(evento);

					if (eventSearched != null) {

						if("OK".equalsIgnoreCase(eventSearchService.insertEvent(eventSearched))){
							mensajesAlerta.setCodigo("01");
							mensajesAlerta.setParam(evento.getId());
							mensajesAlerta.setMensaje("Evento creado exitosamente.");
							return mensajesAlerta;
						}
					}


					eventoRepository.delete(evento.getId());
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al crear el evento.");




					return mensajesAlerta;

				}



				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setParam(evento.getId());
				mensajesAlerta.setMensaje("Evento creado exitosamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al crear evento.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al crear evento.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta update(Evento evento){
		try {
			Evento objEvento = eventoRepository.findById(evento.getId());
			evento.setUsuarioCreacion(objEvento.getUsuarioCreacion());
			evento.setFechaCreacion(objEvento.getFechaCreacion());
			evento.setFechaModificacion(now);
			if(evento.getMaxEntradas() < 0)
				evento.setMaxEntradas(20);
			
			if(evento.getEstado() != "P") {
				if(evento.getModoVenta() == "C" && objEvento.getModoVenta() != "C") {
					evento.setFechaInicioCanje(now);
				}else if(evento.getModoVenta() == "N" && objEvento.getModoVenta() != "N") {
					evento.setFechaInicioNormal(now);
				}else if(evento.getModoVenta() == "P" && objEvento.getModoVenta() != "P") {
					evento.setFechaInicioPreventa(now);
				}
				
				if(objEvento.getModoVenta() != null && objEvento.getModoVenta() != "") {
					if(objEvento.getModoVenta() == "C" && evento.getModoVenta() != "C") {
						evento.setFechaInicioCanje(now);
					}else if(objEvento.getModoVenta() == "N" && evento.getModoVenta() != "N") {
						evento.setFechaInicioNormal(now);
					}else if(objEvento.getModoVenta() == "P" && evento.getModoVenta() != "P") {
						evento.setFechaInicioPreventa(now);
					}
				}
					
			}
			
			if(objEvento.isCancelado() == false && evento.isCancelado() == true) {
				objEvento.setFechaCancelado(now);
				if("NOK".equalsIgnoreCase(eventSearchService.deleteEvent(evento.getId()))){
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al actualizar evento.");
					return mensajesAlerta;
				}
			}
			
			if(eventoRepository.save(evento) != null && "A".equalsIgnoreCase(evento.getEstado())) {
				logger.info("Se va a insertar evento, por metodo \"update\" ");

				EventSearched eventSearched = eventSearchService.getSearchEvent(evento);

				if (eventSearched == null || "NOK".equalsIgnoreCase(eventSearchService.insertEvent(eventSearched))) {
					//objEvento.setEstado(eventoAnterior);
					eventoRepository.save(objEvento);
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al actualizar el evento.");
					return mensajesAlerta;
				}

				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Evento actualizado exitosamente.");
			}
			else {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al actualizar evento.");
			}
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("Hubo un error en el sistema al actualizar evento.");
			logger.info("Error: " + e);
		}
		return mensajesAlerta;
	}
	public MensajesAlerta setStatus(EventoQuery query){
		try {
			Evento objEvento = eventoRepository.findById(query.getId());
			//String  eventoAnterior = objEvento.getEstado();
			objEvento.setEstado(query.getEstado());
			objEvento.setFechaModificacion(now);
			if(objEvento.isCancelado() == false && query.isCancelado() == true) {
				if("NOK".equalsIgnoreCase(eventSearchService.deleteEvent(query.getId()))){
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al actualizar evento.");
					return mensajesAlerta;
				}
				objEvento.setFechaCancelado(now);
			}
			String estado = "";

			EventSearched eventSearched = eventSearchService.getSearchEvent(objEvento);

			if(eventSearched != null && objEvento.isPublicado() && "A".equalsIgnoreCase(query.getEstado())) {
				System.out.println("Se va actualizar estado de evento");
				if ("NOK".equalsIgnoreCase(eventSearchService.insertEvent(eventSearched))) {
					//objEvento.setEstado(eventoAnterior);
					//eventoRepository.save(objEvento);
					mensajesAlerta.setCodigo("00");
					mensajesAlerta.setMensaje("Hubo un error al actualizar el evento.");
					return mensajesAlerta;
				}
			}

			if(eventoRepository.save(objEvento) != null) {
				if(objEvento.getEstado().equals("A")) {
					List<PrecioLocalidad> precios = preciosRepository.findByIdEvento(objEvento.getId());
					for(PrecioLocalidad precio : precios) {
						IngresoInventario newIngreso = new IngresoInventario();	
						List<ItemServicioValor> valoresItemServicio = precio.getValoresServicios();
						double valorServicio = 0;
						double valorPrecio = 0;
						
						for(ItemServicioValor vis : valoresItemServicio){
						    //Obtiene los valores
							if(vis.getIdItemServicio().equals(PrecioLocalidad.idValorServicio))
								valorServicio = vis.getValor();
							if(vis.getIdItemServicio().equals(PrecioLocalidad.idPrecio))
								valorPrecio = vis.getValor();
						}
						
						String idLocalidad = precio.getIdLocalidad();
						Localidad localidad = localidadRepository.findById(idLocalidad);
						Integer capacidad = localidad.getCapacidadLocalidad();
						
						
						newIngreso.setIdTipoIngreso("CREACION");
						newIngreso.setIdTransaccion("PRODUCTCATALOG");
						newIngreso.setIdLocalidad(idLocalidad);
						newIngreso.setCantidad(capacidad);
						newIngreso.setPrecio(valorServicio + valorPrecio);
						newIngreso.setEstado("A");
						Object obj = inventarioComponent.setIngresoInventario(newIngreso);
						System.out.println(obj);
					}
				}
				if(query.getEstado().equals("A"))
					estado = "activado";
				else
					estado = "inactivado";
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Evento "+estado+" correctamente.");
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
	
	public MensajesAlerta publishEvent(EventoQuery query){
		try {
			Evento objEvento = eventoRepository.findById(query.getId());
			objEvento.setPublicado(true);
			objEvento.setFechaModificacion(now);
			EventSearched eventSearched = eventSearchService.getSearchEvent(objEvento);

			if(eventSearched == null){
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al publicar el evento, intente nuevamente.");
				return mensajesAlerta;
			}
			if ("NOK".equalsIgnoreCase(eventSearchService.insertEvent(eventSearched))) {
				mensajesAlerta.setCodigo("00");
				mensajesAlerta.setMensaje("Hubo un error al publicar el evento, intente nuevamente.");
			}
			else {
				objEvento.setPublicado(true);
				eventoRepository.save(objEvento);
				mensajesAlerta.setCodigo("01");
				mensajesAlerta.setMensaje("Evento publicado correctamente.");
			}
			
			return mensajesAlerta;
		}catch(Exception e) {
			mensajesAlerta.setCodigo("00");
			mensajesAlerta.setMensaje("problema en el sistema al publicar el evento.");
			logger.info("Error: " + e);
			return mensajesAlerta;
		}
	}
	
	public ArrayList<Hashtable<String, String>> getInfoPreciosLocalidad(String idEvento) {
		/** ESTE METODO GENERA UN LISTADO DE LOCALIDADES CON SUS RESPECTIVOS PRECIOS **/
		/** LOS CUALES SE MOSTRARAN EN LA PRESENTACION DE LAS MISMAS. **/
		logger.info("Inicio Metodo Precios Loc");
		List<Localidad> localidades = localidadRepository.findByIdEventoOrderByIdLocalidadEstandarAsc(idEvento);
		//Hashtable<String, String> precioLoc = new Hashtable<String, String>();
		ArrayList<Hashtable<String, String>> preciosLoc = new ArrayList<Hashtable<String, String>>();
		//preciosLoc.put("España","Madrid");
	 
		for(Localidad localidad : localidades) {
			Hashtable<String, String> precioLoc = new Hashtable<String, String>();
			precioLoc.put("id", localidad.getId());
			precioLoc.put("nombre", localidad.getNombre());
			precioLoc.put("capacidad", localidad.getCapacidadLocalidad().toString());
			
			List<PrecioLocalidad> precios = preciosRepository.findByIdLocalidad(localidad.getId());
			if(precios.size() > 0) {
				logger.info("Precios: " + precios.toString());
				PrecioLocalidad precio = precios.get(0);
				List<ItemServicioValor> valoresItemServicio = precio.getValoresServicios();
				double valorServicio = 0;
				double valorPrecio = 0;
				System.out.println("VIS sizw: "+ valoresItemServicio.size());
				for(ItemServicioValor vis : valoresItemServicio){
				    //Obtiene los valores
					System.out.println("VIS: "+ vis.toString());
					if(vis.getIdItemServicio().equals(PrecioLocalidad.idValorServicio))
						valorServicio = vis.getValor();
					if(vis.getIdItemServicio().equals(PrecioLocalidad.idPrecio))
						valorPrecio = vis.getValor();
				}
				Double precioUnit = valorServicio + valorPrecio;
				Double precioTotal = precioUnit;
				
				List<ImpuestoEvento> impuestos = impuestoRepository.findByIdEvento(idEvento);
				for(ImpuestoEvento impuesto : impuestos) {
					logger.info("Ident: " +impuesto.getTipoImpuesto().getIdentificador());
					String identImp = impuesto.getTipoImpuesto().getIdentificador();
					if(identImp.equals("IMP_AGG")) {
						double porcImp = impuesto.getValor() / 100;
						double valorImp = porcImp * precioUnit;
						precioTotal = precioTotal + valorImp; 
					}					
				}
				
				precioLoc.put("precio", precioUnit.toString());
				precioLoc.put("precioTotal", precioTotal.toString());
				preciosLoc.add(precioLoc);
			}
		}
		return preciosLoc;
	}

	public Pedido getPreciosLocalidadPedido(Pedido pedido) {
		logger.info("Inicio Metodo Component");
		List<Localidad> localidades = localidadRepository.findByIdEventoOrderByIdLocalidadEstandarAsc(pedido.getIdEvento());
		ArrayList<LocalidadPedido> localidadesPedido = new ArrayList<LocalidadPedido>();
		ArrayList<LocalidadPedido> localidadesUpdate = new ArrayList<LocalidadPedido>();
		ObjectMapper mapper = new ObjectMapper();
		//ArrayList<LinkedHashMap> pedidoCompleto = new ArrayList<LinkedHashMap>();
		//try {
			localidadesPedido = pedido.getLocalidades();
			logger.info("Inicio localidades");
			for(LocalidadPedido localidadPedido : localidadesPedido) {
				//for(Localidad localidad : localidades) {
					//if(localidad.equals(localidadPedido.getIdLocalidad())) {
						//Obtiene el precio de la localidad
						//List<PrecioLocalidad> precios = preciosRepository.findByIdLocalidad(localidad.getId());
						List<PrecioLocalidad> precios = preciosRepository.findByIdLocalidad(localidadPedido.getIdLocalidad());
						if(precios.size() > 0) {
						logger.info("Precios: " + precios.toString());
						PrecioLocalidad precio = precios.get(0);
						List<ItemServicioValor> valoresItemServicio = precio.getValoresServicios();
						double valorServicio = 0;
						double valorPrecio = 0;

						for(ItemServicioValor vis : valoresItemServicio){
						    //Obtiene los valores
							if(vis.getIdItemServicio().toUpperCase().equals(PrecioLocalidad.idValorServicio))
								valorServicio = vis.getValor();
							if(vis.getIdItemServicio().toUpperCase().equals(PrecioLocalidad.idPrecio))
								valorPrecio = vis.getValor();
						}
						localidadPedido.setPrecio(valorServicio + valorPrecio);
						logger.info(localidadPedido.getNombreLocalidades() + " = " + localidadPedido.getPrecio());
						ArrayList<Fares> faresLoc = new ArrayList<Fares>();
						Fares fares = new Fares();
						logger.info("Get impuestos");
						List<ImpuestoEvento> impuestos = impuestoRepository.findByIdEvento(pedido.getIdEvento());
						for(ImpuestoEvento impuesto : impuestos) {
							logger.info("Ident: " +impuesto.getTipoImpuesto().getIdentificador());
							fares.setIdFares(impuesto.getIdImpuesto());
							fares.setTipoFares(impuesto.getTipoImpuesto().getIdentificador());
							fares.setTipoTransaccion("SUMA");
							fares.setDescripcion("");
							fares.setValor(impuesto.getValor()+ "%");

							faresLoc.add(fares);
						}
						logger.info("Fin for");
						localidadPedido.setFares(faresLoc);
					//}
				//}
						localidadesUpdate.add(localidadPedido);
				}
			}
			logger.info("Fin Precios");
			pedido.setLocalidades(localidadesUpdate);

			/*LLAMADA METODO EN FARES*/
			logger.info("Llamada Fares");
			LinkedHashMap pedidoCompleto = (LinkedHashMap) faresComponent.getDescuentosPedido(pedido);
			logger.info("Pedido completo");
			logger.info(pedidoCompleto.toString());

			Pedido pedidoMapper = mapper.convertValue(pedidoCompleto, Pedido.class);
		/*
		}catch(Exception e) {
			logger.error("An ERROR Message");
		}//*/
		return pedidoMapper;
	}
	
	public ArrayList<DescuentoLocalidad> getListDescuentosLocalidades(String idEvento) {
		/********************** ESTE METODO GENERA UN LISTADO DE LOCALIDADES CON SUS DESCUENTOS ****************/
		logger.info("Inicio Metodo Precios Loc");
		List<Localidad> localidades = localidadRepository.findByIdEventoOrderByIdLocalidadEstandarAsc(idEvento);
		ArrayList<DescuentoLocalidad> listDescuentos = new ArrayList<DescuentoLocalidad>();
		DescuentoLocalidad descuentosLocalidadesMapper = new DescuentoLocalidad();
		ObjectMapper mapper = new ObjectMapper();
		for(Localidad localidad : localidades) {
			DescuentoLocalidad descuentosLocalidad = new DescuentoLocalidad();
			descuentosLocalidad.setIdEvento(idEvento);
			descuentosLocalidad.setIdLocalidad(localidad.getId());
			descuentosLocalidad.setNombre(localidad.getNombre());
			descuentosLocalidad.setDescripcion(localidad.getDescripcion());
			
			List<PrecioLocalidad> precios = preciosRepository.findByIdLocalidad(localidad.getId());
			if(precios.size() > 0) {
				logger.info("Precios: " + precios.toString());
				PrecioLocalidad precio = precios.get(0);
				List<ItemServicioValor> valoresItemServicio = precio.getValoresServicios();
				double valorServicio = 0;
				double valorPrecio = 0;
				System.out.println("VIS sizw: "+ valoresItemServicio.size());
				for(ItemServicioValor vis : valoresItemServicio){
				    //Obtiene los valores
					System.out.println("VIS: "+ vis.toString());
					if(vis.getIdItemServicio().equals(PrecioLocalidad.idValorServicio))
						valorServicio = vis.getValor();
					if(vis.getIdItemServicio().equals(PrecioLocalidad.idPrecio))
						valorPrecio = vis.getValor();
				}
				Double precioUnit = valorServicio + valorPrecio;
				Double precioTotal = precioUnit;
				
				List<ImpuestoEvento> impuestos = impuestoRepository.findByIdEvento(idEvento);
				for(ImpuestoEvento impuesto : impuestos) {
					logger.info("Ident: " +impuesto.getTipoImpuesto().getIdentificador());
					String identImp = impuesto.getTipoImpuesto().getIdentificador();
					if(identImp.equals("IMP_AGG")) {
						double porcImp = impuesto.getValor() / 100;
						double valorImp = porcImp * precioUnit;
						precioTotal = precioTotal + valorImp; 
					}					
				}
				descuentosLocalidad.setPrecio(precioTotal);
			}
			logger.info("Llamada Fares");
			 LinkedHashMap descuentosLocalidadesResp = (LinkedHashMap) faresComponent.getDescuentoByIdLocalidad(descuentosLocalidad);
			 descuentosLocalidadesMapper = mapper.convertValue(descuentosLocalidadesResp, DescuentoLocalidad.class);
			logger.info("listDescuentosLocalidades completo");
			
		}
		
		listDescuentos.add(descuentosLocalidadesMapper);
		
		//ArrayList<DescuentoLocalidad> descuentosMapper = mapper.convertValue(listDescuentosLocalidades,	TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, DescuentoLocalidad.class));
		
		return listDescuentos;
	}
}


















