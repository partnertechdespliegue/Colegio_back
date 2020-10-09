package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Receso;
import com.mitocode.model.Turno;

public interface RecesoRepo extends JpaRepository<Receso, Integer>{

	Receso findByIdReceso (Integer idReceso);
	List<Receso> findByTurno (Turno turno);
	
}
