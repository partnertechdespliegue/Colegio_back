package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Curso;

public interface CursoRepo extends JpaRepository<Curso, Integer>{

	Curso findByIdCurso(Integer idCurso);
	
	@Query(value="SELECT cu.* FROM curso cu INNER JOIN tipo_curso tc on cu.id_tipo_curso = tc.id_tipo_curso\r\n" + 
			"where tc.id_colegio = :idColegio", nativeQuery = true)
	public List<Curso> listarPorColegio(@Param("idColegio") Integer idColegio);

}
