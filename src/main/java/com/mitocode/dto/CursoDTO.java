package com.mitocode.dto;

import java.util.ArrayList;
import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Curso;
import com.mitocode.model.Empleado;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Tema;
import com.mitocode.model.TipoCurso;

public class CursoDTO {

	private Curso curso;
	private TipoCurso tipoCurso;
	private Colegio colegio;
	private Empleado empleado;
	private Tema tema;
	private NivelEducativo nivelEducativo;
	private Grado grado;
	private List<Tema> lsTema;

	public CursoDTO() {
		super();
		this.lsTema = new ArrayList<Tema>();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
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

	public List<Tema> getLsTema() {
		return lsTema;
	}

	public void setLsTema(List<Tema> lsTema) {
		this.lsTema = lsTema;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
