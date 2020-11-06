package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Parametro;

public interface ParametroService extends ICRUD<Parametro>{

	Parametro encontrarPorCodigoYColegio(String codigo, Colegio colegio);

	List<Parametro> listarPorColegio(Colegio colegio);

}
