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
@Table(name = "horario_seccion")
public class HorarioSeccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHorarioSeccion;
	
	@Column(name = "hora_duracion", nullable = false)
	private Integer horaDuracion;
	
	@Column(name = "minuto_duracion", nullable = false)
	private Integer minutoDuracion;

	@Column(name = "hora_inicio", nullable = false)
	private Timestamp horaInicio;

	@Column(name = "hora_fin", nullable = false)
	private Timestamp horaFin;

	@ManyToOne
	@JoinColumn(name = "id_dia_laboral", nullable = false)
	private DiaLaboral diaLaboral;

	@ManyToOne
	@JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;

	public Integer getIdHorarioSeccion() {
		return idHorarioSeccion;
	}

	public void setIdHorarioSeccion(Integer idHorarioSeccion) {
		this.idHorarioSeccion = idHorarioSeccion;
	}

	public Integer getHoraDuracion() {
		return horaDuracion;
	}

	public void setHoraDuracion(Integer horaDuracion) {
		this.horaDuracion = horaDuracion;
	}

	public Integer getMinutoDuracion() {
		return minutoDuracion;
	}

	public void setMinutoDuracion(Integer minutoDuracion) {
		this.minutoDuracion = minutoDuracion;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timestamp getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}

	public DiaLaboral getDiaLaboral() {
		return diaLaboral;
	}

	public void setDiaLaboral(DiaLaboral diaLaboral) {
		this.diaLaboral = diaLaboral;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
