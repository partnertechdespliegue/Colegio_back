package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;

public interface ApoderadoService extends ICRUD<Apoderado>{

	List<Apoderado> listarPorColegio(Colegio colegio);

	List<Apoderado> listarPorSucursal(Sucursal sucursal);

	Boolean existePorNroDoc(String nroDoc);

}
