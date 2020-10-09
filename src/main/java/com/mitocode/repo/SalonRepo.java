package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;

public interface SalonRepo extends JpaRepository<Salon, Integer>{
	
	Salon findByIdSalon (Integer idSalon);
	List<Salon> findBySucursal (Sucursal sucursal);

}
