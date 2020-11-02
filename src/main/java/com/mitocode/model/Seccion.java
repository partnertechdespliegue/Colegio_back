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
@Table(name = "seccion")
public class Seccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSeccion;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "capacidad_salon", nullable = false)
	private Integer capacidadSalon;

	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = false)
	private Sucursal sucursal;

	@ManyToOne
	@JoinColumn(name = "id_salon", nullable = false)
	private Salon salon;

	@ManyToOne
	@JoinColumn(name = "id_turno", nullable = false)
	private Turno turno;

	@ManyToOne
	@JoinColumn(name = "id_nivel_educativo", nullable = false)
	private NivelEducativo nivelEducativo;

	@ManyToOne
	@JoinColumn(name = "id_grado", nullable = false)
	private Grado grado;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seccion")
	private List<SeccionEstudiante> lsSeccionEstudiante;

	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Integer getCapacidadSalon() {
		return capacidadSalon;
	}

	public void setCapacidadSalon(Integer capacidadSalon) {
		this.capacidadSalon = capacidadSalon;
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

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

}
