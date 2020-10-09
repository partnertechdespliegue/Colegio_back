package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;

public interface GradoRepo extends JpaRepository<Grado, Integer> {

	List<Grado> findByNivelEducativo(NivelEducativo nivelEducativo);
	
}
