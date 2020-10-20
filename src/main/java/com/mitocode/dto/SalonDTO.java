package com.mitocode.dto;

import com.mitocode.model.Colegio;
import com.mitocode.model.Curso;
import com.mitocode.model.DiaLaboral;
import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;

public class SalonDTO {

	private Salon salon;
	private Curso curso;
	private HorarioSalon horarioSalon;
	private Colegio colegio;
	private Sucursal sucursal;
	private DiaLaboral diaLaboral;

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public HorarioSalon getHorarioSalon() {
		return horarioSalon;
	}

	public void setHorarioSalon(HorarioSalon horarioSalon) {
		this.horarioSalon = horarioSalon;
	}

	public DiaLaboral getDiaLaboral() {
		return diaLaboral;
	}

	public void setDiaLaboral(DiaLaboral diaLaboral) {
		this.diaLaboral = diaLaboral;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

}
