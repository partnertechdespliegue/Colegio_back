package com.mitocode.dto;

import java.util.ArrayList;
import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;

public class ColegioDTO {

	private Colegio colegio;
	private Sucursal sucursal;
	private List<Sucursal> lsSucursal;
	private List<Integer> lsDiaLaboral;

	public ColegioDTO() {
		super();
		lsSucursal = new ArrayList<Sucursal>();
		lsDiaLaboral = new ArrayList<Integer>();
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<Sucursal> getLsSucursal() {
		return lsSucursal;
	}

	public void setLsSucursal(List<Sucursal> lsSucursal) {
		this.lsSucursal = lsSucursal;
	}

	public List<Integer> getLsDiaLaboral() {
		return lsDiaLaboral;
	}

	public void setLsDiaLaboral(List<Integer> lsDiaLaboral) {
		this.lsDiaLaboral = lsDiaLaboral;
	}

}
