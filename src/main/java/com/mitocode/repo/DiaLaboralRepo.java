package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Sucursal;

public interface DiaLaboralRepo extends JpaRepository<DiaLaboral, Integer>{
	
	DiaLaboral findByIdDiaLaboral (Integer idDiaLaboral);
	List<DiaLaboral> findBySucursalOrderByNumDiaAsc (Sucursal sucursal);
	boolean existsBySucursalAndNumDia (Sucursal sucursal, Integer numDia);

}
