package com.ts.productCatalog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ts.productCatalog.entity.Empresario;

public interface EmpresarioRepository extends MongoRepository<Empresario, String>{
	
	List<Empresario> findByIdAndEstado(String idEvento,String estado);
	List<Empresario> findByIdEventoAndEstado(String idEvento,String estado);
	Empresario findByIdEvento(String idEvento);
	List<Empresario> findByEstado(String estado);
	Empresario findById(String id);
}
