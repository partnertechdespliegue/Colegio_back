package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "parametro")
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idParametro;

	@NotNull(message = "La descripcion del parametro no puede estar vacio")
	@Length(message = "La descripcion del parametro no puede exceder los 100 caracteres", min = 0, max = 100)
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;

	@Length(message = "El codigo del parametro no puede exceder los 20 caracteres", min = 0, max = 20)
	@Column(name = "codigo", nullable = false, length = 20)
	private String codigo;

	@Column(name = "valor", nullable = true)
	private String valor;

	@Column(name = "estado", nullable = true)
	private Integer estado;

	@Length(message = "El grupo del parametro no puede exceder los 50 caracteres", min = 0, max = 50)
	@Column(name = "grupo", nullable = true, length = 50)
	private String grupo;

	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = true)
	private Colegio colegio;

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

}
