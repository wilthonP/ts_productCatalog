package com.ts.productCatalog.component;

import java.util.Map;

//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	/*TipoEventoComponent searchComponent;
	
	@Autowired
	public Receiver(TipoEventoComponent searchComponent){
		this.searchComponent = searchComponent;
	}
	@Bean
	Queue queue() {
		return new Queue("SearchQ", false);
	}
	
	@RabbitListener(queues = "SearchQ")
    public void processMessage(Map<String,Object> fare) {
       System.out.println(fare);
       //searchComponent.updateTipoEvento((String)fare.get("FLIGHT_NUMBER"),(String)fare.get("FLIGHT_DATE"),(int)fare.get("NEW_INVENTORY"));
       //call repository and update the fare for the given flight
    }*/	
}