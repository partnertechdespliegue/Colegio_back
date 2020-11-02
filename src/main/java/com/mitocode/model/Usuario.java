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
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@NotNull(message = "El nombre de usuario no puede estar vacio")
	@Length(message = "El nombre de usuario no debe exceder los 55 caracteres", min = 0, max = 55)
	@Column(name = "username", nullable = false, length = 55, unique = true)
	private String username;

	@NotNull(message = "La contraseña de usuario no puede estar vacio")
	@Length(message = "La contraseña de usuario no debe exceder los 100 caracteres", min = 0, max = 100)
	@Column(name = "password", nullable = false, length = 100)
	private String password;

//	@Length(message="El email de usuario no debe exceder los 100 caracteres",min=0,max=100)
//	@Email(message="El email debe tener un formato valido")
//	@Column(name = "email", nullable = true, length = 100, unique=true)
//	private String email;

	@NotNull(message = "El estado de usuario no puede estar vacio")
	@Column(name = "estado", nullable = false)
	private Boolean estado;

	@ManyToOne
	@JoinColumn(name = "id_perfil", nullable = false)
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "id_estudiante", nullable = true)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "id_apoderado", nullable = true)
	private Apoderado apoderado;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

}
