package com.mitocode.dto;

import java.util.ArrayList;
import java.util.List;

import com.mitocode.model.Receso;
import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;

public class TurnoDTO {
	
	private Turno turno;
	private Receso receso;
	private Sucursal sucursal;
	private List<Receso> lsReceso;
	
	public TurnoDTO() {
		super();
		lsReceso = new ArrayList<Receso>();
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Receso getReceso() {
		return receso;
	}
	public void setReceso(Receso receso) {
		this.receso = receso;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public List<Receso> getLsReceso() {
		return lsReceso;
	}
	public void setLsReceso(List<Receso> lsReceso) {
		this.lsReceso = lsReceso;
	}

}
