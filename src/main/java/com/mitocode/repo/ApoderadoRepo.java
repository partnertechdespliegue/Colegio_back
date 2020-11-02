package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;

public interface ApoderadoRepo extends JpaRepository<Apoderado, Integer>{

	Apoderado findByIdApoderado(Integer idApoderado);
	List<Apoderado> findByColegio(Colegio colegio);
	List<Apoderado> findBySucursal(Sucursal sucursal);
	
}
