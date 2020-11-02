package com.mitocode.dto;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Relacion;
import com.mitocode.model.TipoRelacion;

public class RelacionDTO {

	private Relacion relacion;
	private TipoRelacion tipoRelacion;
	private Estudiante estudiante;
	private Apoderado apoderado;
	private Colegio colegio;

	public Relacion getRelacion() {
		return relacion;
	}

	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}

	public TipoRelacion getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(TipoRelacion tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

}
