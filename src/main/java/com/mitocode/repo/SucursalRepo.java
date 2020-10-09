package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;

public interface SucursalRepo extends JpaRepository<Sucursal, Integer> {

	Sucursal findByIdSucursal(Integer idSucursal);
	List<Sucursal> findByColegio(Colegio colegio);
}
