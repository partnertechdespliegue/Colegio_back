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
@Table(name = "puesto_colegio")
public class PuestoColegio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPuestoColegio;

	@Column(nullable = false)
	private String descripcion;

	@Column(name = "cod_puesto", nullable = false)
	private Integer codPuesto;
	// 0-> registro normal / 1 -> Maestro
	
	@ManyToOne
	@JoinColumn(name = "id_departamento_colegio", nullable = true)
	private DepartamentoColegio departamentoColegio;

	public PuestoColegio() {
		super();
	}

	public PuestoColegio(String descripcion, Integer codPuesto,
			DepartamentoColegio departamentoColegio) {
		super();
		this.descripcion = descripcion;
		this.codPuesto = codPuesto;
		this.departamentoColegio = departamentoColegio;
	}

	public Integer getIdPuestoColegio() {
		return idPuestoColegio;
	}

	public void setIdPuestoColegio(Integer idPuestoColegio) {
		this.idPuestoColegio = idPuestoColegio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DepartamentoColegio getDepartamentoColegio() {
		return departamentoColegio;
	}

	public void setDepartamentoColegio(DepartamentoColegio departamentoColegio) {
		this.departamentoColegio = departamentoColegio;
	}

	public Integer getCodPuesto() {
		return codPuesto;
	}

	public void setCodPuesto(Integer codPuesto) {
		this.codPuesto = codPuesto;
	}

}
