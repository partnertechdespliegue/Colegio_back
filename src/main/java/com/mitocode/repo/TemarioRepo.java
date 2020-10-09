package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Curso;
import com.mitocode.model.Tema;
import com.mitocode.model.Temario;

public interface TemarioRepo extends JpaRepository<Temario, Integer>{
	
	Temario findByIdTemario(Integer idTemario);
	List<Temario> findByCurso(Curso curso);
	boolean existsByCursoAndTema(Curso curso, Tema tema);

}
