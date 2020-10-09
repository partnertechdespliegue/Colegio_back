package com.mitocode.dto;

import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;

public class SalonDTO {
	
	private Salon salon;
	private Sucursal sucursal;
	
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
}
