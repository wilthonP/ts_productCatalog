package com.ts.productCatalog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ts.productCatalog.entity.ImpuestoEvento;

public interface ImpuestoEventoRepository extends MongoRepository< ImpuestoEvento, String>{
	List<ImpuestoEvento> findByIdEvento(String idEvento);
	List<ImpuestoEvento> findByEstado(String estado);
	ImpuestoEvento findById(String id);
	void deleteById(String id);

}
