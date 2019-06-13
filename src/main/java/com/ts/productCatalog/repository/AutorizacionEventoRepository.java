package com.ts.productCatalog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ts.productCatalog.entity.AutorizacionEvento;

public interface AutorizacionEventoRepository extends MongoRepository<AutorizacionEvento, String>{
	
	List<AutorizacionEvento> findByIdAndEstado(String idEvento,String estado);
	List<AutorizacionEvento> findByIdEventoAndEstado(String idEvento,String estado);
	AutorizacionEvento 		 findByNumAutorizacionAndIdEvento(String numAutorizacion,String idEvento);
	AutorizacionEvento 		 findByNumAutorizacion(String numAutorizacion);
	List<AutorizacionEvento> findByIdEvento(String idEvento);
	List<AutorizacionEvento> findByEstado(String estado);
	AutorizacionEvento findById(String id);
	void deleteById(String id);
}
