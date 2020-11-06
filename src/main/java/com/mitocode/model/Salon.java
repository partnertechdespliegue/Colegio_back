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
@Table(name = "salon")
public class Salon {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idSalon;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "tipo_salon", nullable = false)
	private Integer tipoSalon;
	// 1 -> unico / 2 -> compartido
	
	@Column(name = "capacidad", nullable = false)
	private Integer capacidad;
	
	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = false)
	private Sucursal sucursal;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "salon")
	private List<HorarioSalon> lsHorarioSalon;

	public Integer getIdSalon() {
		return idSalon;
	}

	public void setIdSalon(Integer idSalon) {
		this.idSalon = idSalon;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTipoSalon() {
		return tipoSalon;
	}

	public void setTipoSalon(Integer tipoSalon) {
		this.tipoSalon = tipoSalon;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
