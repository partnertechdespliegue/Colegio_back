package com.mitocode.model;

import java.sql.Timestamp;
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
@Table(name = "sucursal")
public class Sucursal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSucursal;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Column(name = "hora_incio_atencion", nullable = false)
	private Timestamp horaInicioAtencion;
	
	@Column(name = "hora_fin_atencion", nullable = false)
	private Timestamp horaFinAtencion;
	
	@Column(name = "nivel_inicial", nullable = false)
	private Boolean nivelInicial;
	
	@Column(name = "nivel_primaria", nullable = false)
	private Boolean nivelPrimaria;
	
	@Column(name = "nivel_secundaria", nullable = false)
	private Boolean nivelSecundaria;
	
	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = false)
	private Colegio colegio;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento", nullable = false)
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name = "id_provincia", nullable = false)
	private Provincia provincia;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito", nullable = false)
	private Distrito distrito;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
	private List<Turno> lsTurno;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
	private List<DiaLaboral> lsDiaLaboral;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursal")
	private List<Salon> lsSalon;

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Timestamp getHoraInicioAtencion() {
		return horaInicioAtencion;
	}

	public void setHoraInicioAtencion(Timestamp horaInicioAtencion) {
		this.horaInicioAtencion = horaInicioAtencion;
	}

	public Timestamp getHoraFinAtencion() {
		return horaFinAtencion;
	}

	public void setHoraFinAtencion(Timestamp horaFinAtencion) {
		this.horaFinAtencion = horaFinAtencion;
	}

	public Boolean getNivelInicial() {
		return nivelInicial;
	}

	public void setNivelInicial(Boolean nivelInicial) {
		this.nivelInicial = nivelInicial;
	}

	public Boolean getNivelPrimaria() {
		return nivelPrimaria;
	}

	public void setNivelPrimaria(Boolean nivelPrimaria) {
		this.nivelPrimaria = nivelPrimaria;
	}

	public Boolean getNivelSecundaria() {
		return nivelSecundaria;
	}

	public void setNivelSecundaria(Boolean nivelSecundaria) {
		this.nivelSecundaria = nivelSecundaria;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

}
