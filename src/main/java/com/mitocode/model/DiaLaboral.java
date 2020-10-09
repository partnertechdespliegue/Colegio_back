package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dia_laboral")
public class DiaLaboral {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idDiaLaboral;
	
	@Column(name = "num_dia", nullable = false)
	private Integer numDia;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = false)
	private Sucursal sucursal;

	public Integer getIdDiaLaboral() {
		return idDiaLaboral;
	}

	public void setIdDiaLaboral(Integer idDiaLaboral) {
		this.idDiaLaboral = idDiaLaboral;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getNumDia() {
		return numDia;
	}

	public void setNumDia(Integer numDia) {
		this.numDia = numDia;
	}

}
