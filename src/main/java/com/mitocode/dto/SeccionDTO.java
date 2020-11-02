package com.mitocode.dto;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Curso;
import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Salon;
import com.mitocode.model.Seccion;
import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;

public class SeccionDTO {

	private Seccion seccion;
	private Sucursal sucursal;
	private Colegio colegio;
	private Turno turno;
	private Salon salon;
	private Curso curso;
	private HorarioSeccion horarioSeccion;
	private DiaLaboral diaLaboral;
	private NivelEducativo nivelEducativo;
	private Grado grado;
	private List<Estudiante> lsEstudiante;

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public NivelEducativo getNivelEducativo() {
		return nivelEducativo;
	}

	public void setNivelEducativo(NivelEducativo nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public List<Estudiante> getLsEstudiante() {
		return lsEstudiante;
	}

	public void setLsEstudiante(List<Estudiante> lsEstudiante) {
		this.lsEstudiante = lsEstudiante;
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

	public HorarioSeccion getHorarioSeccion() {
		return horarioSeccion;
	}

	public void setHorarioSeccion(HorarioSeccion horarioSeccion) {
		this.horarioSeccion = horarioSeccion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
