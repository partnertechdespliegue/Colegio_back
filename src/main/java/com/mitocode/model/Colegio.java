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
@Table(name = "colegio")
public class Colegio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idColegio;

	@Column(name = "nombre", nullable = true)
	private String nombre;

	@Column(name = "cod_ugel", nullable = true)
	private String codUgel;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "colegio")
	private List<Sucursal> lsSucursal;

	public Integer getIdColegio() {
		return idColegio;
	}

	public void setIdColegio(Integer idColegio) {
		this.idColegio = idColegio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodUgel() {
		return codUgel;
	}

	public void setCodUgel(String codUgel) {
		this.codUgel = codUgel;
	}

	public List<Sucursal> getLsSucursal() {
		return lsSucursal;
	}

	public void setLsSucursal(List<Sucursal> lsSucursal) {
		this.lsSucursal = lsSucursal;
	}
	
}
