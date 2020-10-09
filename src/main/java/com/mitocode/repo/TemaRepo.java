package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Tema;

public interface TemaRepo extends JpaRepository<Tema, Integer>{
	
	Tema findByIdTema (Integer idTema);
	
	@Query(value="SELECT te.* FROM tema te INNER JOIN tipo_curso tc on te.id_tipo_curso = tc.id_tipo_curso\r\n" + 
			"where tc.id_colegio = :idColegio", nativeQuery = true)
	public List<Tema> listarPorColegio(@Param("idColegio") Integer idColegio);

}
