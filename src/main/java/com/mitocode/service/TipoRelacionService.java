package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.TipoRelacion;

public interface TipoRelacionService extends ICRUD<TipoRelacion>{

	List<TipoRelacion> listarPorColegio(Colegio colegio);

	List<TipoRelacion> registrarList(List<TipoRelacion> lsTipoRelacion);

}
