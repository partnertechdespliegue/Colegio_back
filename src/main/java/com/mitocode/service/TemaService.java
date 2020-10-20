package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Tema;
import com.mitocode.model.TipoCurso;

public interface TemaService extends ICRUD<Tema>{

	List<Tema> listarPorColegio(Integer idColegio);

	List<Tema> listarPorTipoCurso(TipoCurso tipoCurso);

}
