package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Colegio;
import com.mitocode.model.Empleado;
import com.mitocode.model.Sucursal;

public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{

	Empleado findByIdEmpleado(Integer idEmpleado);
	List<Empleado> findByColegio(Colegio colegio);
	List<Empleado> findBySucursal(Sucursal sucursal);
	
	@Query(value="SELECT em.* FROM public.empleado em\r\n"
			+ "INNER JOIN contrato co on co.id_contrato = em.id_contrato\r\n"
			+ "INNER JOIN puesto_colegio pc on pc.id_puesto_colegio = co.id_puesto_colegio\r\n"
			+ "WHERE em.id_colegio = :idColegio AND pc.cod_puesto = :codPuesto", nativeQuery = true)
	public List<Empleado> listarPorColegioCodPuesto(@Param("idColegio") Integer idColegio, @Param("codPuesto") Integer codPuesto);
	
	@Query(value="SELECT em.* FROM public.empleado em\r\n"
			+ "INNER JOIN contrato co on co.id_contrato = em.id_contrato\r\n"
			+ "INNER JOIN puesto_colegio pc on pc.id_puesto_colegio = co.id_puesto_colegio\r\n"
			+ "WHERE em.id_sucursal = :idSucursal AND pc.cod_puesto = :codPuesto", nativeQuery = true)
	public List<Empleado> listarPorSucursalCodPuesto(@Param("idSucursal") Integer idSucursal, @Param("codPuesto") Integer codPuesto);
	
}
