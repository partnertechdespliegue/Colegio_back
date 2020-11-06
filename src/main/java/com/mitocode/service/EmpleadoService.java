package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Empleado;
import com.mitocode.model.Sucursal;

public interface EmpleadoService extends ICRUD<Empleado>{

	List<Empleado> listarPorColegio(Colegio colegio);

	List<Empleado> listarPorSucursal(Sucursal sucursal);

}
