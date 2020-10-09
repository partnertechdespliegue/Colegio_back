package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;

public interface TurnoRepo extends JpaRepository<Turno, Integer>{
	
	Turno findByIdTurno (Integer idTurno);
	List<Turno> findBySucursal (Sucursal sucursal);

}
