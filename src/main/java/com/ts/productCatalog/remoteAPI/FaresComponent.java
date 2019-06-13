package com.ts.productCatalog.remoteAPI;

import java.util.Arrays;
import java.util.Collections;

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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableEurekaClient
@Component
public class FaresComponent extends PagedResources<Object>{

	private static final Logger logger = LoggerFactory.getLogger(FaresComponent.class);
	private String entidad = "fares";
	private RestTemplate restTemplate;
	private String faresServiceHost;
	private long faresServicePort;
	private boolean useRibbon;
	private LoadBalancerClient loadBalancer;
	

	@Autowired
	public FaresComponent(
			@Value("${fares.service.host:fares}") String faresServiceHost,
			@Value("${fares.service.port:8080}") long faresServicePort,
			@Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
		super();
		this.restTemplate = getRestTemplate();
		this.faresServiceHost = faresServiceHost;
		this.faresServicePort = faresServicePort;
		this.useRibbon = useRibbon;
	}

	@Autowired(required = false)
	public void setLoadBalancer(LoadBalancerClient loadBalancer) {
		this.loadBalancer = loadBalancer;
	}

	protected RestTemplate getRestTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		converter.setObjectMapper(mapper);

		return new RestTemplate(
				Collections.<HttpMessageConverter<?>>singletonList(converter));
	}

	public Object getRequest(String strRequest, Object params) {
		String url=faresURL()+strRequest;
		logger.info(url);
		
		Object result = getRestTemplate().postForObject(url, params, Object.class);
		logger.info("error"+result.toString());
		return result;

	}

	private String faresURL() {
		String url;
		if (useRibbon) {
			System.out.println("A");
			ServiceInstance instance = loadBalancer.choose(entidad.toUpperCase());
			url = String.format("http://%s:%s/"+entidad+"/"+entidad+"/", instance.getHost(), instance.getPort());
		} else {
			System.out.println("B");
			ServiceInstance instance = loadBalancer.choose("zuul".toUpperCase());
			url = String.format("http://%s:%s/bffwebadmin/bffBack/"+entidad+"/", instance.getHost(), instance.getPort());
		}
		
		logger.info(url);
		return url;

	}
	
	public Object getDescuentoProductoByIdEvento(Object query) {
		return getRequest("getDescuentoProductoByIdEvento",query); 
	}
	
	public Object getDescuentosPedido(Object query) {
		return getRequest("getDescuentosPedido",query); 
	}
	
	public Object getDescuentoByIdLocalidad(Object query) {
		System.out.println(query);
		return getRequest("getDescuentoByIdLocalidad",query); 
	}
}

