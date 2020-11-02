package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seccion_estudiante")
public class SeccionEstudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSeccionEstudiante;

	@ManyToOne
	@JoinColumn(name = "id_seccion", nullable = false)
	private Seccion seccion;

	@ManyToOne
	@JoinColumn(name = "id_estudiante", nullable = false)
	private Estudiante estudiante;

	public SeccionEstudiante() {
		super();
	}

	public SeccionEstudiante(Seccion seccion, Estudiante estudiante) {
		super();
		this.seccion = seccion;
		this.estudiante = estudiante;
	}

	public Integer getIdSeccionEstudiante() {
		return idSeccionEstudiante;
	}

	public void setIdSeccionEstudiante(Integer idSeccionEstudiante) {
		this.idSeccionEstudiante = idSeccionEstudiante;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}
