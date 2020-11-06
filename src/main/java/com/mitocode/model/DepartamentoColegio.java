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
@Table(name = "departamento_colegio")
public class DepartamentoColegio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDepartamentoColegio;

	@Column(nullable = false)
	private String descripcion;

	@Column(name = "cod_departamento", nullable = false)
	private Integer codDepartamento;
	// 0-> registro normal / 1 -> Docencia

	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = false)
	private Colegio colegio;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentoColegio")
	private List<PuestoColegio> lsPuestoColegio;

	public DepartamentoColegio() {
		super();
	}

	public DepartamentoColegio(String descripcion, Integer codDepartamento, Colegio colegio) {
		super();
		this.descripcion = descripcion;
		this.codDepartamento = codDepartamento;
		this.colegio = colegio;
	}

	public Integer getIdDepartamentoColegio() {
		return idDepartamentoColegio;
	}

	public void setIdDepartamentoColegio(Integer idDepartamentoColegio) {
		this.idDepartamentoColegio = idDepartamentoColegio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public Integer getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(Integer codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
}
