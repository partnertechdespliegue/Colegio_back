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
@Table(name = "tipo_relacion")
public class TipoRelacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoRelacion;

	@Column(nullable = false)
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = false)
	private Colegio colegio;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoRelacion")
	private List<Relacion> lsRelacion;

	public Integer getIdTipoRelacion() {
		return idTipoRelacion;
	}

	public void setIdTipoRelacion(Integer idTipoRelacion) {
		this.idTipoRelacion = idTipoRelacion;
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

}
