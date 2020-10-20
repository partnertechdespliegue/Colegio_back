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
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diaLaboral")
	private List<HorarioSalon> lsHorarioSalon;

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

	public List<HorarioSalon> getLsHorarioSalon() {
		return lsHorarioSalon;
	}

	public void setLsHorarioSalon(List<HorarioSalon> lsHorarioSalon) {
		this.lsHorarioSalon = lsHorarioSalon;
	}

}
