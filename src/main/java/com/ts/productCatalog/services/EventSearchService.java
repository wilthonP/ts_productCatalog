package com.ts.productCatalog.services;


import static java.util.Collections.singletonMap;

import java.io.IOException;
import java.util.Map;

import com.ts.productCatalog.entity.Stage;
import com.ts.productCatalog.entity.Evento;
import com.ts.productCatalog.entity.EventType;
import com.ts.productCatalog.remoteAPI.CatalogRepository;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ts.productCatalog.entity.EventSearched;

@Service
public class EventSearchService {
    @Autowired
    RestHighLevelClient client;

    @Autowired
    private CatalogRepository catalogRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventSearchService.class);


    public EventSearched getSearchEvent(Evento evento) {
        EventType queryTypeEvent = new EventType();
        queryTypeEvent.setId(evento.getTipoActividad());
        EventType resultTypeEvent = catalogRepository.getEventTypeById(queryTypeEvent);


        Stage scenarioQuery = new Stage();
        scenarioQuery.setId(evento.getIdEscenario());
        Stage scenarioResult= catalogRepository.getStageById(scenarioQuery);

        if(resultTypeEvent == null || scenarioResult == null){
            logger.info("Hubo un error al obtener informacion de metadataCatalog");
            return null;
        }


        logger.info("Informacion de tipo de Evento {} ", resultTypeEvent.getDescripcion());
        logger.info("Informacion de escenario {} ", scenarioResult.getDescripcion());

        EventSearched eventSearched = new EventSearched(evento.getId(),evento.getNombre(),evento.getObservaciones(),"",
                resultTypeEvent.getDescripcion(),scenarioResult.getNombre(),evento.getFechaHoraEvento(), scenarioResult.getDireccion());

        return eventSearched;

    }
    public String insertEvent(EventSearched event){
        logger.info("Se va a insertar en elasticsearch "+new Gson().toJson(event));
        String document = new Gson().toJson(event);
        IndexRequest request = new IndexRequest("events","_doc", event.getId()).source(document, XContentType.JSON);
        IndexResponse response = null;
        request.timeout(TimeValue.timeValueSeconds(2));
        try {
            response = client.index(request, RequestOptions.DEFAULT);
            logger.info("Responde elasticsearch {} ",response.getResult());
            logger.info("Responde elasticsearch {} ",response.status());
            if (response.getResult() == DocWriteResponse.Result.CREATED) {
                logger.info("OK elasticsearch");
                return "OK";
            }
            
            return "NOK";
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error al insertar evento en elasticsearch {}", e.getMessage());
            return "NOK";
        }
    }


    public String updateEvent(Object value, String key, String id){
        Map<String, Object> json = singletonMap(key, value);
        UpdateRequest request = new UpdateRequest("events","_doc",id).doc(json);
        request.timeout(TimeValue.timeValueSeconds(2));
        try {
            UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
            if(updateResponse.getResult() == DocWriteResponse.Result.UPDATED){
                return "OK";
            }
            return "NOK";
        } catch (IOException e) {
            return "NOK";
        }
    }

    public String deleteEvent(String id){
        logger.info("Se va a borrar evento de elasticsearch con id {}", id);
        DeleteRequest request = new DeleteRequest("events","_doc",id);
        request.timeout(TimeValue.timeValueSeconds(2));
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            if(response.getResult() == DocWriteResponse.Result.DELETED){
                return "OK";
            }
            return "NOK";
        } catch (IOException e) {
            return "NOK";
        }

    }

}
