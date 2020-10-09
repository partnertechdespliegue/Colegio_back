package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.TipoCurso;

public interface TipoCursoService extends ICRUD<TipoCurso>{

	List<TipoCurso> listarPorColegio(Colegio colegio);

}
