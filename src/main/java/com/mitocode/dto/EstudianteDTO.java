package com.mitocode.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Departamento;
import com.mitocode.model.Distrito;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Pais;
import com.mitocode.model.Provincia;
import com.mitocode.model.Sucursal;
import com.mitocode.model.TipoDoc;
import com.mitocode.model.TipoZona;
import com.mitocode.model.Usuario;

public class EstudianteDTO {

	private Estudiante estudiante;
	private Apoderado apoderado;
	private Sucursal sucursal;
	private Colegio colegio;
	private TipoZona tipoZona;
	private NivelEducativo nivelEducativo;
	private Grado grado;
	private Pais pais;
	private TipoDoc tipoDoc;
	private Departamento departamento;
	private Provincia provincia;
	private Distrito distrito;
	private Usuario usuario;

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public TipoZona getTipoZona() {
		return tipoZona;
	}

	public void setTipoZona(TipoZona tipoZona) {
		this.tipoZona = tipoZona;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public TipoDoc getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
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

}
