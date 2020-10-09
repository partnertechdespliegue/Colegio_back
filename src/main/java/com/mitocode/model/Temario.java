package com.mitocode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "temario")
public class Temario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTemario;
	
	@ManyToOne
	@JoinColumn(name = "id_curso", nullable = false)
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "id_tema", nullable = false)
	private Tema tema;

	public Integer getIdTemario() {
		return idTemario;
	}

	public void setIdTemario(Integer idTemario) {
		this.idTemario = idTemario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
