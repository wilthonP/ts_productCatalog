package com.ts.productCatalog.remoteAPI;


import com.ts.productCatalog.entity.Stage;
import com.ts.productCatalog.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogRepository {

    @Autowired
    RestTemplate restTemplate;

    @Value("${catalog.entity.name}")
    private String catalogEntity;

    @Autowired
    private LoadBalancerClient loadBalancer;

    private static final Logger logger = LoggerFactory.getLogger(CatalogRepository.class);

    public String getCustomUrl(){
        ServiceInstance instance = loadBalancer.choose("zuul".toUpperCase());
        // String url = String.format("http://%s:%s/bffwebadmin/bffBack/"+catalogEntity+"/", instance.getHost(), instance.getPort());
        //String url = String.format("http://%s:%s/bffwebadmin/bffBack/"+catalogEntity+"/", "ec2-18-231-147-38.sa-east-1.compute.amazonaws.com", "8080");
        String url = String.format("http://%s:%s/metaDataCatalog/"+catalogEntity+"/", "ec2-18-231-147-38.sa-east-1.compute.amazonaws.com", "8097");
        return url;
    }


    public EventType getEventTypeById(EventType tipo){
        String url = getCustomUrl()+"getTipoEventoById";
        try{
            restTemplate.postForObject(url, tipo, EventType.class);
        }catch (Exception e){
            logger.error("Error al ejecutar getEventTypeById {} ", e);
            return null;
        }
        return restTemplate.postForObject(url, tipo, EventType.class);
    }


    public Stage getStageById(Stage stage){
        String url = getCustomUrl()+"getEscenarioEstandar";
        try{
            restTemplate.postForObject(url, stage, EventType.class);
        }catch (Exception e){
            logger.error("Error al ejecutar getStageById {} ", e);
            return null;
        }
        return restTemplate.postForObject(url, stage, Stage.class);
    }





}
