package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.Parametro;

public interface ParametroRepo extends JpaRepository<Parametro, Integer>  {
	
	Parametro findByCodigoAndColegio(String codigo, Colegio colegio);

	Parametro findByCodigo(String codigo);
	
}
