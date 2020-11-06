package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.PuestoColegio;

public interface PuestoColegioRepo extends JpaRepository<PuestoColegio, Integer>{

	PuestoColegio findByIdPuestoColegio (Integer id);
	
	@Query(value="SELECT pc.* FROM public.puesto_colegio pc\r\n"
			+ "INNER JOIN departamento_colegio dc on dc.id_departamento_colegio = pc.id_departamento_colegio\r\n"
			+ "INNER JOIN colegio cl on cl.id_colegio = dc.id_colegio\r\n"
			+ "where cl.id_colegio = :idColegio", nativeQuery = true)
	public List<PuestoColegio> listarPorColegio(@Param("idColegio") Integer idColegio);	
}
