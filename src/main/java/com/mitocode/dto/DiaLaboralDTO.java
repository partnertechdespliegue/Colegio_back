package com.mitocode.dto;

import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Sucursal;

public class DiaLaboralDTO {
	
	private DiaLaboral diaLaboral;
	private Sucursal sucursal;
	
	public DiaLaboral getDiaLaboral() {
		return diaLaboral;
	}
	public void setDiaLaboral(DiaLaboral diaLaboral) {
		this.diaLaboral = diaLaboral;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
