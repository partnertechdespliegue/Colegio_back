package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.TipoRelacion;

public interface TipoRelacionRepo extends JpaRepository<TipoRelacion, Integer>{

	List<TipoRelacion> findByColegio (Colegio colegio);
	
}
