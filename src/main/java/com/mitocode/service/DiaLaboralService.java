package com.mitocode.service;

import java.util.List;

import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Sucursal;

public interface DiaLaboralService extends ICRUD<DiaLaboral>{

	List<DiaLaboral> listarPorSucursal(Sucursal sucursal);

	Boolean existePorNumDia(Sucursal sucursal, Integer numDia);

}
