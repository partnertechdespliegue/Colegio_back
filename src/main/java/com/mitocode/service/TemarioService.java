package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Curso;
import com.mitocode.model.Tema;
import com.mitocode.model.Temario;

public interface TemarioService extends ICRUD<Temario>{

	List<Temario> listarPorCurso(Curso curso);

	Boolean existePorCursoYTema(Curso curso, Tema tema);

}
