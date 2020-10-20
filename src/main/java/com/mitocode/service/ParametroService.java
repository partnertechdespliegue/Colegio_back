package com.mitocode.service;

import com.mitocode.model.Colegio;
import com.mitocode.model.Parametro;

public interface ParametroService extends ICRUD<Parametro>{

	Parametro encontrarPorCodigoYColegio(String codigo, Colegio colegio);

}
