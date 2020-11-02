package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.TipoCurso;

public interface TipoCursoRepo extends JpaRepository<TipoCurso, Integer>{

	TipoCurso findByIdTipoCurso(Integer idTipoCurso);
	List<TipoCurso> findByColegio(Colegio colegio);

}
