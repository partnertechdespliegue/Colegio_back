package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Curso;

public interface CursoService extends ICRUD<Curso>{

	List<Curso> listarPorColegio(Integer idColegio);

}
