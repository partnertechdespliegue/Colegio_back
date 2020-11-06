package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.Empleado;
import com.mitocode.model.Sucursal;

public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{

	Empleado findByIdEmpleado(Integer idEmpleado);
	List<Empleado> findByColegio(Colegio colegio);
	List<Empleado> findBySucursal(Sucursal sucursal);
	
}
