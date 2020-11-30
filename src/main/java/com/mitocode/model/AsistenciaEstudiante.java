package com.mitocode.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asistencia_estudiante")
public class AsistenciaEstudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAsistenciaEstudiante;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@Column(name = "fecha_asistencia", nullable = false)
	private Timestamp fechaAsistencia;

	@ManyToOne
	@JoinColumn(name = "id_estudiante", nullable = false)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "id_tipo_asistencia", nullable = false)
	private TipoAsistencia tipoAsistencia;

	@ManyToOne
	@JoinColumn(name = "id_horario_seccion", nullable = false)
	private HorarioSeccion horarioSeccion;

	public AsistenciaEstudiante() {
		super();
	}

	public AsistenciaEstudiante(Estudiante estudiante, HorarioSeccion horarioSeccion, Timestamp fechaAsistencia) {
		super();
		this.estudiante = estudiante;
		this.horarioSeccion = horarioSeccion;
		this.fechaAsistencia = fechaAsistencia;
	}

	public Integer getIdAsistenciaEstudiante() {
		return idAsistenciaEstudiante;
	}

	public void setIdAsistenciaEstudiante(Integer idAsistenciaEstudiante) {
		this.idAsistenciaEstudiante = idAsistenciaEstudiante;
	}

	public Timestamp getFechaAsistencia() {
		return fechaAsistencia;
	}

	public void setFechaAsistencia(Timestamp fechaAsistencia) {
		this.fechaAsistencia = fechaAsistencia;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
