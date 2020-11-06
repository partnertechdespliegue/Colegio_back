package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.DepartamentoColegio;

public interface DepartamentoColegioRepo extends JpaRepository<DepartamentoColegio, Integer>{

	DepartamentoColegio findByIdDepartamentoColegio(Integer id);
	
	List<DepartamentoColegio> findByColegio (Colegio colegio);
	
}
