package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;

public interface TurnoService extends ICRUD<Turno>{

	List<Turno> listarPorSucursal(Sucursal sucursal);

}
