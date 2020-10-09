package com.mitocode.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "hora_semanal", nullable = false)
	private Integer horaSemanal;
	
	@ManyToOne
	@JoinColumn(name = "id_nivel_educativo", nullable = false)
	private NivelEducativo nivelEducativo;
	
	@ManyToOne
	@JoinColumn(name = "id_grado", nullable = false)
	private Grado grado;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_curso", nullable = false)
	private TipoCurso tipoCurso;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
	private List<Temario> lsTemario;

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getHoraSemanal() {
		return horaSemanal;
	}

	public void setHoraSemanal(Integer horaSemanal) {
		this.horaSemanal = horaSemanal;
	}

	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
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

}
