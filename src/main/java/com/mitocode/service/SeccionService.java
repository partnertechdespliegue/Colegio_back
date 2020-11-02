package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Seccion;
import com.mitocode.model.Sucursal;

public interface SeccionService extends ICRUD<Seccion>{

	List<Seccion> listarPorSucursal(Sucursal sucursal);

}
