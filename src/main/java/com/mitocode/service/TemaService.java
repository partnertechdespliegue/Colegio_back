package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Tema;

public interface TemaService extends ICRUD<Tema>{

	List<Tema> listarPorColegio(Integer idColegio);

}
