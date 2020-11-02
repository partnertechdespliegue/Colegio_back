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
@Table(name = "estudiante")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstudiante;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apePaterno;

	@Column(nullable = false)
	private String apeMaterno;

	@Column(nullable = false)
	private String nroDoc;

	@Column(nullable = false)
	private Timestamp fechaNacimiento;

	@Column(nullable = true)
	private String nroCelular;

	@Column(nullable = true)
	private String nroTelefono;

	@Column(nullable = true)
	private String correo;

	@Column(nullable = false)
	private String sexo;

	@Column(nullable = false)
	private String direccion;

	@Column(nullable = true)
	private String referencia;

	@Column(nullable = true)
	private String foto;

	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = true)
	private Sucursal sucursal;

	@ManyToOne
	@JoinColumn(name = "id_colegio", nullable = false)
	private Colegio colegio;

	@ManyToOne
	@JoinColumn(name = "id_tipo_doc", nullable = false)
	private TipoDoc tipoDoc;
	
	@ManyToOne
	@JoinColumn(name = "id_nivel_educativo", nullable = false)
	private NivelEducativo nivelEducativo;
	
	@ManyToOne
	@JoinColumn(name = "id_grado", nullable = false)
	private Grado grado;

	@ManyToOne
	@JoinColumn(name = "id_pais", nullable = true)
	private Pais pais;

	@ManyToOne
	@JoinColumn(name = "id_departamento", nullable = false)
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name = "id_provincia", nullable = false)
	private Provincia provincia;

	@ManyToOne
	@JoinColumn(name = "id_distrito", nullable = false)
	private Distrito distrito;

	@ManyToOne
	@JoinColumn(name = "id_tipo_zona", nullable = true)
	private TipoZona tipoZona;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
	private List<Relacion> lsRelacion;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
	private List<Usuario> lsUsuario;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
	private List<SeccionEstudiante> lsSeccionEstudiante;

	public Integer getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Integer idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNroCelular() {
		return nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	public String getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public List<Relacion> getLsRelacion() {
		return lsRelacion;
	}

	public void setLsRelacion(List<Relacion> lsRelacion) {
		this.lsRelacion = lsRelacion;
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

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public List<Usuario> getLsUsuario() {
		return lsUsuario;
	}

	public void setLsUsuario(List<Usuario> lsUsuario) {
		this.lsUsuario = lsUsuario;
	}

	public NivelEducativo getNivelEducativo() {
		return nivelEducativo;
	}

	public void setNivelEducativo(NivelEducativo nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

}
