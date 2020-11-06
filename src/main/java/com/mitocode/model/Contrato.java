package com.mitocode.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contrato")
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idContrato;

	@Column(name = "fecha_inicio", nullable = false)
	private Timestamp fechaInicio;

	@Column(name = "fecha_fin", nullable = true)
	private Timestamp fechaFin;

	@Column(name = "sueldo_base", nullable = false)
	private Double sueldoBase;

//	@Column(name = "valor_hora", nullable = true)
//	private Double valorHora;
	
	@Column(name = "tipo_pago", nullable = false)
	private Integer tipoPago;
	// 1 - deposito cuenta / 2 - efectivo / 3 - otro

	@Column(name = "nro_cta", length = 50, nullable = true)
	private String nroCta;
	
	@Column(name = "nro_cta_inter", length = 50, nullable = true)
	private String nroCtaInter;

	@Column(name = "tipo_cuenta", nullable = true)
	private Integer tipoCuenta;
	// 1 - corriente / 2 - ahorros / 3 - interbancaria

	@Column(name = "tipo_moneda", nullable = true)
	private Integer tipoMoneda;
	// 1 - corriente / 2 - ahorros / 3 - interbancaria
	
	@Column(name = "fecha_firma_contrato", nullable = true)
	private Timestamp fechaFirma;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "contrato")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name = "id_departamento_colegio", nullable = true)
	private DepartamentoColegio departamentoColegio;

	@ManyToOne
	@JoinColumn(name = "id_puesto_colegio", nullable = true)
	private PuestoColegio puestoColegio;

	@ManyToOne
	@JoinColumn(name = "id_banco_sueldo", nullable = true)
	private Banco bancoSueldo;

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(Double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public String getNroCta() {
		return nroCta;
	}

	public void setNroCta(String nroCta) {
		this.nroCta = nroCta;
	}

	public String getNroCtaInter() {
		return nroCtaInter;
	}

	public void setNroCtaInter(String nroCtaInter) {
		this.nroCtaInter = nroCtaInter;
	}

	public Integer getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(Integer tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Integer getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(Integer tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public Timestamp getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(Timestamp fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public DepartamentoColegio getDepartamentoColegio() {
		return departamentoColegio;
	}

	public void setDepartamentoColegio(DepartamentoColegio departamentoColegio) {
		this.departamentoColegio = departamentoColegio;
	}

	public PuestoColegio getPuestoColegio() {
		return puestoColegio;
	}

	public void setPuestoColegio(PuestoColegio puestoColegio) {
		this.puestoColegio = puestoColegio;
	}

	public Integer getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Banco getBancoSueldo() {
		return bancoSueldo;
	}

	public void setBancoSueldo(Banco bancoSueldo) {
		this.bancoSueldo = bancoSueldo;
	}
	
}
