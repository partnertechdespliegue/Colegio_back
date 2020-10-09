package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;

public interface SucursalService extends ICRUD<Sucursal>{

	List<Sucursal> listarPorColegio(Colegio colegio);

}
