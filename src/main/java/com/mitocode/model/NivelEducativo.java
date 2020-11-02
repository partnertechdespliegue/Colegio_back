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
@Table(name = "nivel_educativo")
public class NivelEducativo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNivelEducativo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = false)
	private Colegio colegio;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelEducativo")
	private List<Grado> lsGrado;

	public Integer getIdNivelEducativo() {
		return idNivelEducativo;
	}

	public void setIdNivelEducativo(Integer idNivelEducativo) {
		this.idNivelEducativo = idNivelEducativo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Grado> getLsGrado() {
		return lsGrado;
	}

	public void setLsGrado(List<Grado> lsGrado) {
		this.lsGrado = lsGrado;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

}
