package com.ts.productCatalog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ts.productCatalog.entity.TicketSeguro;

public interface TicketSeguroRepository extends MongoRepository<TicketSeguro, String>{

	List<TicketSeguro> findByIdEvento(String idEvento);
	List<TicketSeguro> findByIdEscenario(String idEscenario);
	List<TicketSeguro> findByIdLocalidad(String idLocalidad);
	List<TicketSeguro> findByEstado(String estado);
	TicketSeguro findById(String id);
}
