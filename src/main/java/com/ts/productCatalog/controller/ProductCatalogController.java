package com.ts.productCatalog.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

//import org.apache.camel.json.simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ts.productCatalog.component.TicketSeguroComponent;
import com.ts.productCatalog.component.AutorizacionEventoComponent;
import com.ts.productCatalog.component.DisenioTicketLocalidadComponent;
import com.ts.productCatalog.component.EmpresarioComponent;
import com.ts.productCatalog.component.EventoComponent;
import com.ts.productCatalog.component.FormaPagoEventoComponent;
import com.ts.productCatalog.component.ImpuestoEventoComponent;
import com.ts.productCatalog.component.LocalidadComponent;
import com.ts.productCatalog.component.PrecioLocalidadComponent;
import com.ts.productCatalog.component.SecuencialLocalidadComponent;
import com.ts.productCatalog.component.ValorLocalidadComponent;
import com.ts.productCatalog.entity.AutorizacionEvento;
import com.ts.productCatalog.entity.DescuentoLocalidad;
import com.ts.productCatalog.entity.DisenioTicketLocalidad;
import com.ts.productCatalog.entity.Empresario;
import com.ts.productCatalog.entity.Evento;
import com.ts.productCatalog.entity.FormaPagoEvento;
import com.ts.productCatalog.entity.ImpuestoEvento;
import com.ts.productCatalog.entity.Localidad;
import com.ts.productCatalog.entity.MensajesAlerta;
import com.ts.productCatalog.entity.Pedido;
import com.ts.productCatalog.entity.PrecioLocalidad;
import com.ts.productCatalog.entity.SecuencialLocalidad;
import com.ts.productCatalog.entity.TicketSeguro;
import com.ts.productCatalog.entity.ValorLocalidad;

@CrossOrigin
@RestController
@RequestMapping("/productCatalog")
public class ProductCatalogController {
	
	private EventoComponent eventoComponent;
	private EmpresarioComponent empresarioComponent;
	private AutorizacionEventoComponent autorizacionEventoComponent;
	private PrecioLocalidadComponent precioLocalidadComponent;
	private SecuencialLocalidadComponent secuencialLocalidadComponent;
	private DisenioTicketLocalidadComponent disenioTicketLocalidadComponent;
	private ValorLocalidadComponent valorLocalidadComponent;
	private LocalidadComponent localidadComponent;
	private FormaPagoEventoComponent formaPagoEventoComponent;
	private TicketSeguroComponent ticketSeguroComponent;
	private ImpuestoEventoComponent impuestoEventoComponent;

	@Autowired
	public ProductCatalogController(EventoComponent eventoComponent, EmpresarioComponent empresarioComponent,
			AutorizacionEventoComponent autorizacionEventoComponent,PrecioLocalidadComponent precioLocalidadComponent,
			SecuencialLocalidadComponent secuencialLocalidadComponent, DisenioTicketLocalidadComponent disenioTicketLocalidadComponent,
			ValorLocalidadComponent valorLocalidadComponent, LocalidadComponent localidadComponent,
			FormaPagoEventoComponent formaPagoEventoComponent, TicketSeguroComponent ticketSeguroComponent,
			ImpuestoEventoComponent impuestoEventoComponent) {
		super();
		this.eventoComponent = eventoComponent;
		this.empresarioComponent = empresarioComponent;
		this.autorizacionEventoComponent = autorizacionEventoComponent;
		this.precioLocalidadComponent = precioLocalidadComponent;
		this.secuencialLocalidadComponent = secuencialLocalidadComponent;
		this.disenioTicketLocalidadComponent = disenioTicketLocalidadComponent;
		this.valorLocalidadComponent = valorLocalidadComponent;
		this.localidadComponent = localidadComponent;
		this.formaPagoEventoComponent = formaPagoEventoComponent;
		this.ticketSeguroComponent = ticketSeguroComponent;
		this.impuestoEventoComponent = impuestoEventoComponent;
	}
	
	/******************************** EVENTOS ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoById", method = RequestMethod.POST)
	List<Evento> searchById(@RequestBody EventoQuery query){
		return eventoComponent.getEventoById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByNombre", method = RequestMethod.POST)
	List<Evento> searchByNombre(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByNombre(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByCiudad", method = RequestMethod.POST)
	List<Evento> searchByCiudad(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByCiudad(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByEventoNacional", method = RequestMethod.POST)
	List<Evento> searchByEventoNacional(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByEventoNacional(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByAplicaPreventa", method = RequestMethod.POST)
	List<Evento> searchByAplicaPreventa(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByAplicaPreventa(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByTipoActividad", method = RequestMethod.POST)
	List<Evento> searchByTipoActividad(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByTipoActividad(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoByEstado", method = RequestMethod.POST)
	List<Evento> searchByEstado(@RequestBody EventoQuery query){
		return eventoComponent.getEventoByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getEventoAll", method = RequestMethod.POST)
	List<Evento> getEventoAll(){
		return eventoComponent.getEventoAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createEvento", method = RequestMethod.POST)
	MensajesAlerta createEvento(@RequestBody Evento evento) throws Exception{
		return eventoComponent.create(evento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updateEvento", method = RequestMethod.POST)
	MensajesAlerta updateEvento(@RequestBody Evento evento) throws Exception{
		return eventoComponent.update(evento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusEvento", method = RequestMethod.POST)
	MensajesAlerta setStatusEvento(@RequestBody EventoQuery query) throws Exception{
		return eventoComponent.setStatus(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/publishEvent", method = RequestMethod.POST)
	MensajesAlerta publishEvent(@RequestBody EventoQuery query) throws Exception{
		return eventoComponent.publishEvent(query);
	}	
	
	/******************************** EMPRESARIO ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcEmpresarioById", method = RequestMethod.POST)
	List<Empresario> searchEmpresarioById(@RequestBody EmpresarioQuery query){
		return empresarioComponent.getEmpresarioById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcEmpresarioByIdEventoAndEstado", method = RequestMethod.POST)
	List<Empresario> getEmpresarioByIdEventoAndEstado(@RequestBody EmpresarioQuery query){
		return empresarioComponent.getEmpresarioByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcEmpresarioByIdEvento", method = RequestMethod.POST)
	Empresario getEmpresarioByIdEvento(@RequestBody EmpresarioQuery query){
		return empresarioComponent.getEmpresarioByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcEmpresarioByEstado", method = RequestMethod.POST)
	List<Empresario> searchEmpresarioByEstado(@RequestBody EmpresarioQuery query){
		return empresarioComponent.getEmpresarioByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcEmpresarioAll", method = RequestMethod.POST)
	List<Empresario> getEmpresarioAll(){
		return empresarioComponent.getEmpresarioAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcEmpresario", method = RequestMethod.POST)
	MensajesAlerta createEmpresario(@RequestBody Empresario empresario) throws Exception{
		return empresarioComponent.create(empresario);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcEmpresario", method = RequestMethod.POST)
	MensajesAlerta updateEmpresario(@RequestBody Empresario empresario) throws Exception{
		return empresarioComponent.update(empresario);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusEmpresario", method = RequestMethod.POST)
	MensajesAlerta setStatusEmpresario(@RequestBody EmpresarioQuery query) throws Exception{
		return empresarioComponent.setStatus(query);
	}
	
	/******************************** AUTORIZACION EVENTO ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoById", method = RequestMethod.POST)
	List<AutorizacionEvento> searchAutorizacionEventoById(@RequestBody AutorizacionEventoQuery query){
		return autorizacionEventoComponent.getAutorizacionEventoById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoByIdEventoAndEstado", method = RequestMethod.POST)
	List<AutorizacionEvento> getAutorizacionEventoByIdEventoAndEstado(@RequestBody AutorizacionEventoQuery query){
		return autorizacionEventoComponent.getAutorizacionEventoByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoByIdEvento", method = RequestMethod.POST)
	List<AutorizacionEvento> getAutorizacionEventoByIdEvento(@RequestBody AutorizacionEventoQuery query){
		return autorizacionEventoComponent.getAutorizacionEventoByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoByNumAutorizacion", method = RequestMethod.POST)
	AutorizacionEvento searchAutorizacionEventoByNumAutorizacion(@RequestBody AutorizacionEventoQuery query){
		return autorizacionEventoComponent.getAutorizacionEventoByNumAutorizacion(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoByEstado", method = RequestMethod.POST)
	List<AutorizacionEvento> searchAutorizacionEventoByEstado(@RequestBody AutorizacionEventoQuery query){
		return autorizacionEventoComponent.getAutorizacionEventoByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcAutorizacionEventoAll", method = RequestMethod.POST)
	List<AutorizacionEvento> getAutorizacionEventoAll(){
		return autorizacionEventoComponent.getAutorizacionEventoAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcAutorizacionEvento", method = RequestMethod.POST)
	MensajesAlerta createAutorizacionEvento(@RequestBody AutorizacionEvento autorizacionEvento) throws Exception{
		return autorizacionEventoComponent.create(autorizacionEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcAutorizacionEvento", method = RequestMethod.POST)
	MensajesAlerta updateAutorizacionEvento(@RequestBody AutorizacionEvento autorizacionEvento) throws Exception{
		return autorizacionEventoComponent.update(autorizacionEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusAutorizacionEvento", method = RequestMethod.POST)
	MensajesAlerta setStatusAutorizacionEvento(@RequestBody AutorizacionEventoQuery query) throws Exception{
		return autorizacionEventoComponent.setStatus(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/deleteAutorizacion", method = RequestMethod.POST)
	MensajesAlerta deleteAutorizacion(@RequestBody AutorizacionEventoQuery query) throws Exception{
		return autorizacionEventoComponent.deleteAutorizacion(query);
	}
	
	/******************************** PRRECIOS POR LOCALIDAD ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcPrecioLocalidadById", method = RequestMethod.POST)
	List<PrecioLocalidad> searchPrecioLocalidadById(@RequestBody PrecioLocalidadQuery query){
		return precioLocalidadComponent.getPrecioLocalidadById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcPrecioLocalidadByIdEventoAndEstado", method = RequestMethod.POST)
	List<PrecioLocalidad> getPrecioLocalidadByIdEventoAndEstado(@RequestBody PrecioLocalidadQuery query){
		return precioLocalidadComponent.getPrecioLocalidadByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcPrecioLocalidadByIdEvento", method = RequestMethod.POST)
	List<PrecioLocalidad> getPrecioLocalidadByIdEvento(@RequestBody PrecioLocalidadQuery query){
		return precioLocalidadComponent.getPrecioLocalidadByIdEvento(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcPrecioLocalidadByEstado", method = RequestMethod.POST)
	List<PrecioLocalidad> searchPrecioLocalidadByEstado(@RequestBody PrecioLocalidadQuery query){
		return precioLocalidadComponent.getPrecioLocalidadByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcPrecioLocalidadAll", method = RequestMethod.POST)
	List<PrecioLocalidad> getPrecioLocalidadAll(){
		return precioLocalidadComponent.getPrecioLocalidadAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcPrecioLocalidad", method = RequestMethod.POST)
	MensajesAlerta createPrecioLocalidad(@RequestBody PrecioLocalidad precioLocalidad) throws Exception{
		return precioLocalidadComponent.create(precioLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcPrecioLocalidad", method = RequestMethod.POST)
	MensajesAlerta updatePrecioLocalidad(@RequestBody PrecioLocalidad precioLocalidad) throws Exception{
		return precioLocalidadComponent.update(precioLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusPrecioLocalidad", method = RequestMethod.POST)
	MensajesAlerta setStatusPrecioLocalidad(@RequestBody PrecioLocalidadQuery query) throws Exception{
		return precioLocalidadComponent.setStatus(query);
	}
	
	/**************************** SECUENCIALES DE LOCALIDADES *************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcSecuencialLocalidadById", method = RequestMethod.POST)
	List<SecuencialLocalidad> searchSecuencialLocalidadById(@RequestBody SecuencialLocalidadQuery query){
		return secuencialLocalidadComponent.getSecuencialLocalidadById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcSecuencialLocalidadByIdEventoAndEstado", method = RequestMethod.POST)
	List<SecuencialLocalidad> getSecuencialLocalidadByIdEventoAndEstado(@RequestBody SecuencialLocalidadQuery query){
		return secuencialLocalidadComponent.getSecuencialLocalidadByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcSecuencialLocalidadByIdEvento", method = RequestMethod.POST)
	List<SecuencialLocalidad> getSecuencialLocalidadByIdEvento(@RequestBody SecuencialLocalidadQuery query){
		return secuencialLocalidadComponent.getSecuencialLocalidadByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcSecuencialLocalidadByEstado", method = RequestMethod.POST)
	List<SecuencialLocalidad> searchSecuencialLocalidadByEstado(@RequestBody SecuencialLocalidadQuery query){
		return secuencialLocalidadComponent.getSecuencialLocalidadByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcSecuencialLocalidadAll", method = RequestMethod.POST)
	List<SecuencialLocalidad> getSecuencialLocalidadAll(){
		return secuencialLocalidadComponent.getSecuencialLocalidadAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcSecuencialLocalidad", method = RequestMethod.POST)
	MensajesAlerta createSecuencialLocalidad(@RequestBody SecuencialLocalidad secuencialLocalidad) throws Exception{
		return secuencialLocalidadComponent.create(secuencialLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcSecuencialLocalidad", method = RequestMethod.POST)
	MensajesAlerta updateSecuencialLocalidad(@RequestBody SecuencialLocalidad secuencialLocalidad) throws Exception{
		return secuencialLocalidadComponent.update(secuencialLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusSecuencialLocalidad", method = RequestMethod.POST)
	MensajesAlerta setStatusSecuencialLocalidad(@RequestBody SecuencialLocalidadQuery query) throws Exception{
		return secuencialLocalidadComponent.setStatus(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/deleteSecuencia", method = RequestMethod.POST)
	MensajesAlerta deleteSecuencia(@RequestBody SecuencialLocalidadQuery query) throws Exception{
		return secuencialLocalidadComponent.deleteSecuencia(query);
	}
	
	/**************************** DISENIO DE TICKETS POR LOCALIDAD *******************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcDisenioTicketLocalidadById", method = RequestMethod.POST)
	List<DisenioTicketLocalidad> searchDisenioTicketLocalidadById(@RequestBody DisenioTicketLocalidadQuery query){
		return disenioTicketLocalidadComponent.getDisenioTicketLocalidadById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcDisenioTicketLocalidadByIdEventoAndEstado", method = RequestMethod.POST)
	List<DisenioTicketLocalidad> getDisenioTicketLocalidadByIdEventoAndEstado(@RequestBody DisenioTicketLocalidadQuery query){
		return disenioTicketLocalidadComponent.getDisenioTicketLocalidadByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcDisenioTicketLocalidadByIdEvento", method = RequestMethod.POST)
	List<DisenioTicketLocalidad> getDisenioTicketLocalidadByIdEvento(@RequestBody DisenioTicketLocalidadQuery query){
		return disenioTicketLocalidadComponent.getDisenioTicketLocalidadByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcDisenioTicketLocalidadByEstado", method = RequestMethod.POST)
	List<DisenioTicketLocalidad> searchDisenioTicketLocalidadByEstado(@RequestBody DisenioTicketLocalidadQuery query){
		return disenioTicketLocalidadComponent.getDisenioTicketLocalidadByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcDisenioTicketLocalidadAll", method = RequestMethod.POST)
	List<DisenioTicketLocalidad> getDisenioTicketLocalidadAll(){
		return disenioTicketLocalidadComponent.getDisenioTicketLocalidadAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcDisenioTicketLocalidad", method = RequestMethod.POST)
	MensajesAlerta createDisenioTicketLocalidad(@RequestBody DisenioTicketLocalidad disenioTicketLocalidad) throws Exception{
		return disenioTicketLocalidadComponent.create(disenioTicketLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcDisenioTicketLocalidad", method = RequestMethod.POST)
	MensajesAlerta updateDisenioTicketLocalidad(@RequestBody DisenioTicketLocalidad disenioTicketLocalidad) throws Exception{
		return disenioTicketLocalidadComponent.update(disenioTicketLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusDisenioTicketLocalidad", method = RequestMethod.POST)
	MensajesAlerta setStatusDisenioTicketLocalidad(@RequestBody DisenioTicketLocalidadQuery query) throws Exception{
		return disenioTicketLocalidadComponent.setStatus(query);
	}
	
	/**************************** VALORES DE LOCALIDAD *******************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcValorLocalidadById", method = RequestMethod.POST)
	List<ValorLocalidad> searchValorLocalidadById(@RequestBody ValorLocalidadQuery query){
		return valorLocalidadComponent.getValorLocalidadById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcValorLocalidadByIdEventoAndEstado", method = RequestMethod.POST)
	List<ValorLocalidad> getValorLocalidadByIdEventoAndEstado(@RequestBody ValorLocalidadQuery query){
		return valorLocalidadComponent.getValorLocalidadByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcValorLocalidadByIdEvento", method = RequestMethod.POST)
	List<ValorLocalidad> getValorLocalidadByIdEvento(@RequestBody ValorLocalidadQuery query){
		return valorLocalidadComponent.getValorLocalidadByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcValorLocalidadByEstado", method = RequestMethod.POST)
	List<ValorLocalidad> searchValorLocalidadByEstado(@RequestBody ValorLocalidadQuery query){
		return valorLocalidadComponent.getValorLocalidadByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcValorLocalidadAll", method = RequestMethod.POST)
	List<ValorLocalidad> getValorLocalidadAll(){
		return valorLocalidadComponent.getValorLocalidadAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcValorLocalidad", method = RequestMethod.POST)
	MensajesAlerta createValorLocalidad(@RequestBody ValorLocalidad valorLocalidad) throws Exception{
		return valorLocalidadComponent.create(valorLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcValorLocalidad", method = RequestMethod.POST)
	MensajesAlerta updateValorLocalidad(@RequestBody ValorLocalidad valorLocalidad) throws Exception{
		return valorLocalidadComponent.update(valorLocalidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusValorLocalidad", method = RequestMethod.POST)
	MensajesAlerta setStatusValorLocalidad(@RequestBody ValorLocalidadQuery query) throws Exception{
		return valorLocalidadComponent.setStatus(query);
	}
	
	/**************************** LOCALIDAD *******************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadById", method = RequestMethod.POST)
	Localidad searchLocalidadById(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadByNombre", method = RequestMethod.POST)
	List<Localidad> getLocalidadByNombre(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadByNombre(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadByIdEventoAndIdLocalidadEstandar", method = RequestMethod.POST)
	List<Localidad> getLocalidadByIdEventoAndIdLocalidadEstandar(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadByIdEventoAndIdLocalidadEstandar(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadByIdEscenario", method = RequestMethod.POST)
	List<Localidad> getLocalidadByIdEscenario(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadByIdEscenario(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadByIdEvento", method = RequestMethod.POST)
	List<Localidad> getLocalidadByIdEvento(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadByEstado", method = RequestMethod.POST)
	List<Localidad> searchLocalidadByEstado(@RequestBody LocalidadQuery query){
		return localidadComponent.getLocalidadByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcLocalidadAll", method = RequestMethod.POST)
	List<Localidad> getLocalidadAll(){
		return localidadComponent.getLocalidadAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcLocalidad", method = RequestMethod.POST)
	MensajesAlerta createLocalidad(@RequestBody Localidad localidad) throws Exception{
		return localidadComponent.create(localidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcLocalidad", method = RequestMethod.POST)
	MensajesAlerta updateLocalidad(@RequestBody Localidad localidad) throws Exception{
		return localidadComponent.update(localidad);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusLocalidad", method = RequestMethod.POST)
	MensajesAlerta setStatusLocalidad(@RequestBody LocalidadQuery query) throws Exception{
		return localidadComponent.setStatus(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/deleteLocalidad", method = RequestMethod.POST)
	MensajesAlerta deleteLocalidad(@RequestBody LocalidadQuery query) throws Exception{
		return localidadComponent.deleteLocalidad(query);
	}
	
	/******************************** FORMAS DE PAGO ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcFormaPagoEventoById", method = RequestMethod.POST)
	FormaPagoEvento searchFormaPagoEventoById(@RequestBody FormaPagoEventoQuery query){
		return formaPagoEventoComponent.getFormaPagoEventoById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcFormaPagoEventoByIdEventoAndEstado", method = RequestMethod.POST)
	List<FormaPagoEvento> getFormaPagoEventoByIdEventoAndEstado(@RequestBody FormaPagoEventoQuery query){
		return formaPagoEventoComponent.getFormaPagoEventoByIdEventoAndEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcFormaPagoEventoByIdEvento", method = RequestMethod.POST)
	FormaPagoEvento getFormaPagoEventoByIdEvento(@RequestBody FormaPagoEventoQuery query){
		return formaPagoEventoComponent.getFormaPagoEventoByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcFormaPagoEventoByEstado", method = RequestMethod.POST)
	List<FormaPagoEvento> searchFormaPagoEventoByEstado(@RequestBody FormaPagoEventoQuery query){
		return formaPagoEventoComponent.getFormaPagoEventoByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcFormaPagoEventoAll", method = RequestMethod.POST)
	List<FormaPagoEvento> getFormaPagoEventoAll(){
		return formaPagoEventoComponent.getFormaPagoEventoAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcFormaPagoEvento", method = RequestMethod.POST)
	MensajesAlerta createFormaPagoEvento(@RequestBody FormaPagoEvento formaPagoEvento) throws Exception{
		return formaPagoEventoComponent.create(formaPagoEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcFormaPagoEvento", method = RequestMethod.POST)
	MensajesAlerta updateFormaPagoEvento(@RequestBody FormaPagoEvento formaPagoEvento) throws Exception{
		return formaPagoEventoComponent.update(formaPagoEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusFormaPagoEvento", method = RequestMethod.POST)
	MensajesAlerta setStatusFormaPagoEvento(@RequestBody FormaPagoEventoQuery query) throws Exception{
		return formaPagoEventoComponent.setStatus(query);
	}
	
	/********************************* Ticket Seguro ***********************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcTicketSeguroById", method = RequestMethod.POST)
	TicketSeguro getTicketSeguroById(@RequestBody TicketSeguroQuery query){
		return ticketSeguroComponent.getTicketSeguroById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcTicketSeguroByIdEvento", method = RequestMethod.POST)
	List<TicketSeguro> getTicketSeguroByIdEvento(@RequestBody TicketSeguroQuery query){
		return ticketSeguroComponent.getTicketSeguroByIdEvento(query);
	}	
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcTicketSeguroByEstado", method = RequestMethod.POST)
	List<TicketSeguro> searchTicketSeguroByEstado(@RequestBody TicketSeguroQuery query){
		return ticketSeguroComponent.getTicketSeguroByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcTicketSeguroAll", method = RequestMethod.POST)
	List<TicketSeguro> getTicketSeguroAll(){
		return ticketSeguroComponent.getTicketSeguroAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcTicketSeguro", method = RequestMethod.POST)
	MensajesAlerta createTicketSeguro(@RequestBody TicketSeguro ticketSeguro) throws Exception{
		return ticketSeguroComponent.create(ticketSeguro);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcTicketSeguro", method = RequestMethod.POST)
	MensajesAlerta updateTicketSeguro(@RequestBody TicketSeguro ticketSeguro) throws Exception{
		return ticketSeguroComponent.update(ticketSeguro);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusTicketSeguro", method = RequestMethod.POST)
	MensajesAlerta setStatusTicketSeguro(@RequestBody TicketSeguroQuery query) throws Exception{
		return ticketSeguroComponent.setStatus(query);
	}
	
	/******************************** IMPUESTOS EVENTO ****************************************/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcImpuestoEventoById", method = RequestMethod.POST)
	ImpuestoEvento getImpuestoEventoById(@RequestBody ImpuestoEventoQuery query){
		return impuestoEventoComponent.getImpuestoEventoById(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcImpuestoEventoByIdEvento", method = RequestMethod.POST)
	List<ImpuestoEvento> getImpuestoEventoByIdEvento(@RequestBody ImpuestoEventoQuery query){
		return impuestoEventoComponent.getImpuestoEventoByIdEvento(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcImpuestoEventoByEstado", method = RequestMethod.POST)
	List<ImpuestoEvento> searchImpuestoEventoByEstado(@RequestBody ImpuestoEventoQuery query){
		return impuestoEventoComponent.getImpuestoEventoByEstado(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPcImpuestoEventoAll", method = RequestMethod.POST)
	List<ImpuestoEvento> getImpuestoEventoAll(){
		return impuestoEventoComponent.getImpuestoEventoAll();
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/createPcImpuestoEvento", method = RequestMethod.POST)
	MensajesAlerta createImpuestoEvento(@RequestBody ImpuestoEvento impuestoEvento) throws Exception{
		return impuestoEventoComponent.create(impuestoEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/updatePcImpuestoEvento", method = RequestMethod.POST)
	MensajesAlerta updateImpuestoEvento(@RequestBody ImpuestoEvento impuestoEvento) throws Exception{
		return impuestoEventoComponent.update(impuestoEvento);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/setStatusImpuestoEvento", method = RequestMethod.POST)
	MensajesAlerta setStatusImpuestoEvento(@RequestBody ImpuestoEventoQuery query) throws Exception{
		return impuestoEventoComponent.setStatus(query);
	}
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/deleteImpuestoEvento", method = RequestMethod.POST)
	MensajesAlerta deleteImpuesto(@RequestBody ImpuestoEventoQuery query) throws Exception{
		return impuestoEventoComponent.deleteImpuesto(query);
	}
	
	/** GET DESCUENTOS PEDIDO CC**/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getPreciosLocalidadPedido", method = RequestMethod.POST)
	Pedido getPreciosLocalidadPedido(@RequestBody Pedido pedido){
		return eventoComponent.getPreciosLocalidadPedido(pedido);
	}
	/** ESTE METODO GENERA UN LISTADO DE LOCALIDADES CON SUS RESPECTIVOS PRECIOS RAUL**/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getInfoPreciosLocalidad", method = RequestMethod.POST)
	ArrayList<Hashtable<String, String>> getInfoPreciosLocalidad(@RequestBody String idEvento){
		return eventoComponent.getInfoPreciosLocalidad(idEvento);
	}
	/** METODO DEVUELVE UNA LISTA DE LOCALIDADES CON SUS PRECIOS Y DESCUENTOS. **/
	@Cacheable(value = "post-single", key = "#query", unless = "#result.shares < 500")
	@RequestMapping(value="/getListDescuentosLocalidades", method = RequestMethod.POST)
	ArrayList<DescuentoLocalidad> getListDescuentosLocalidades(@RequestBody String idEvento){
		return eventoComponent.getListDescuentosLocalidades(idEvento);
	}
}
