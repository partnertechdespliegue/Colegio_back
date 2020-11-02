package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Seccion;
import com.mitocode.model.Sucursal;

public interface SeccionRepo extends JpaRepository<Seccion, Integer>{

	Seccion findByIdSeccion(Integer idSeccion);
	
	List<Seccion> findBySucursal(Sucursal sucursal);	

}
