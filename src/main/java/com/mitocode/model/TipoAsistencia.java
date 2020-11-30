package com.mitocode.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipo_asistencia")
public class TipoAsistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoAsistencia;

	@Column(nullable = false)
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAsistencia")
	private List<AsistenciaEstudiante> lsAsistenciaEstudiante;

	public Integer getIdTipoAsistencia() {
		return idTipoAsistencia;
	}

	public void setIdTipoAsistencia(Integer idTipoAsistencia) {
		this.idTipoAsistencia = idTipoAsistencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
