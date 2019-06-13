package com.ts.productCatalog.remoteAPI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@EnableEurekaClient
@Component
public class InventarioComponent extends PagedResources<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(InventarioComponent.class);
	private String entidad="inventory";
	@Autowired
	private RestTemplate restTemplate;
	private String customerServiceHost;
	private long customerServicePort;
	private boolean useRibbon;
	private LoadBalancerClient loadBalancer;

	static class ListPagedResources extends PagedResources<Object> {

	}

	@Autowired
	public InventarioComponent(
			@Value("${customer.service.host:inventory}") String customerServiceHost,
			@Value("${customer.service.port:8080}") long customerServicePort,
			@Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
		super();
		//this.restTemplate = getRestTemplate();
		this.customerServiceHost = customerServiceHost;
		this.customerServicePort = customerServicePort;
		this.useRibbon = useRibbon;
	}

	@Autowired(required = false)
	public void setLoadBalancer(LoadBalancerClient loadBalancer) {
		this.loadBalancer = loadBalancer;
	}

	public boolean isValidCustomerId(long customerId) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(
					customerURL() + customerId, String.class);
			return entity.getStatusCode().is2xxSuccessful();
		} catch (final HttpClientErrorException e) {
			if (e.getStatusCode().value() == 404)
				return false;
			else
				throw e;
		}
	}

	/*protected RestTemplate getRestTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		converter.setObjectMapper(mapper);

		return new RestTemplate(
				Collections.<HttpMessageConverter<?>>singletonList(converter));
	}*/

	public Object getRequest(String strRequest, Object params) {
		String url=customerURL()+strRequest;
		logger.info(url);
		
		Object result = restTemplate.postForObject(url, params, Object.class);
		logger.info("error"+result.toString());
		return result;

	}

	private String customerURL() {
		String url;
		if (useRibbon) {
			System.out.println("A");
			ServiceInstance instance = loadBalancer.choose(entidad.toUpperCase());
			//logger.info("hellow");
			url = String.format("http://%s:%s/"+entidad+"/", instance.getHost(), instance.getPort());
		} else {
			System.out.println("B");
			ServiceInstance instance = loadBalancer.choose("zuul".toUpperCase());
			url = String.format("http://%s:%s/bffwebadmin/bffBack/"+entidad+"/", instance.getHost(), instance.getPort());
		}
		
		logger.info(url);
		return url;

	}

	public Object getOne(String strRequest,long customerId) {
		return restTemplate.getForObject(customerURL() + customerId,
				InventarioComponent.class);
	}
	
	public Object getAllInventario(){
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("dummy", "");
		return getRequest("getAllInventario",parts); 
	}

	public Object getInventarioLocalidad(Object query) {
		return getRequest("getInventarioLocalidad",query); 
	}
	
	public Object getLocalidadDisponible(Object query) {
		return getRequest("getLocalidadDisponible",query); 
	}
	
	public Object setIngresoInventario(Object query) {
		return getRequest("setIngresoInventario",query); 
	}
	
	public Object updateInventarioPrecio(Object query) {
		return getRequest("updateInventarioPrecio",query); 
	}
}
