package com.mitocode.dto;

import com.mitocode.model.AsistenciaEstudiante;
import com.mitocode.model.Estudiante;
import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.TipoAsistencia;

public class AsistenciaDTO {

	private AsistenciaEstudiante asistenciaEstudiante;
	private Estudiante estudiante;
	private TipoAsistencia tipoAsistencia;
	private HorarioSeccion horarioSeccion;

	public AsistenciaEstudiante getAsistenciaEstudiante() {
		return asistenciaEstudiante;
	}

	public void setAsistenciaEstudiante(AsistenciaEstudiante asistenciaEstudiante) {
		this.asistenciaEstudiante = asistenciaEstudiante;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public TipoAsistencia getTipoAsistencia() {
		return tipoAsistencia;
	}

	public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
		this.tipoAsistencia = tipoAsistencia;
	}

	public HorarioSeccion getHorarioSeccion() {
		return horarioSeccion;
	}

	public void setHorarioSeccion(HorarioSeccion horarioSeccion) {
		this.horarioSeccion = horarioSeccion;
	}

}
