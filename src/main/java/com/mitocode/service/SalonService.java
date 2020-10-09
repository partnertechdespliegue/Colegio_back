package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;

public interface SalonService extends ICRUD<Salon>{

	List<Salon> listarPorSucursal(Sucursal sucursal);

}
