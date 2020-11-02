package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relacion")
public class Relacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRelacion;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_relacion", nullable = false)
	private TipoRelacion tipoRelacion;
	
	@ManyToOne
	@JoinColumn(name = "id_estudiante", nullable = true)
	private Estudiante estudiante;
	
	@ManyToOne
	@JoinColumn(name = "id_apoderado", nullable = true)
	private Apoderado apoderado;

	public Integer getIdRelacion() {
		return idRelacion;
	}

	public void setIdRelacion(Integer idRelacion) {
		this.idRelacion = idRelacion;
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

}
