package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Contrato;

public interface ContratoRepo extends JpaRepository<Contrato, Integer>{

	Contrato findByIdContrato(Integer idContrato);
	
}
