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
@Table(name = "receso")
public class Receso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idReceso;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "hora_inicio", nullable = false)
	private Timestamp horaInicio;
	
	@Column(name = "hora_fin", nullable = false)
	private Timestamp horaFin;

	@ManyToOne
	@JoinColumn(name = "id_turno", nullable = false)
	private Turno turno;

	public Integer getIdReceso() {
		return idReceso;
	}

	public void setIdReceso(Integer idReceso) {
		this.idReceso = idReceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
}
