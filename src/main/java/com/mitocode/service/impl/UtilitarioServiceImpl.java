package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Grado;
import com.mitocode.model.Modulo;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Pagina;
import com.mitocode.model.Perfil;
import com.mitocode.model.Usuario;
import com.mitocode.repo.GradoRepo;
import com.mitocode.repo.ModuloRepo;
import com.mitocode.repo.NivelEducativoRepo;
import com.mitocode.repo.PaginaRepo;
import com.mitocode.repo.PerfilRepo;
import com.mitocode.repo.UsuarioRepo;
import com.mitocode.repo.UtilitarioRepo;
import com.mitocode.service.UtilitarioService;

@Service
public class UtilitarioServiceImpl implements UtilitarioService {

	@Autowired
	UtilitarioRepo repoUtilitario;

	@Autowired
	ModuloRepo repoModulo;

	@Autowired
	PaginaRepo repoPagina;

	@Autowired
	PerfilRepo repoPerfil;

	@Autowired
	UsuarioRepo repoUsuario;
	
	@Autowired
	NivelEducativoRepo repoNivEduRepo;
	
	@Autowired
	GradoRepo repoGrado;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	public String insertarDatosModulo(List<Modulo> obj) {
		try {
			repoModulo.saveAll(obj);
			return "Se insertó correctamente los módulos";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarListaModulos. ERROR : " + e.getMessage());
			throw e;
		}
	}

	public String insertarDatosPagina(List<Pagina> obj) {
		try {
			repoPagina.saveAll(obj);
			return "Se insertó correctamente las Paginas";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarPaginas. ERROR : " + e.getMessage());
			throw e;
		}
	}

	public String insertarDatosPerfil(List<Perfil> obj) {
		try {
			repoPerfil.saveAll(obj);
			return "Se insertó correctamente los perfiles";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarPerfiles. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public String insertarDatosUsuarios(List<Usuario> usuarios) {
		try {
			repoUsuario.saveAll(usuarios);
			return "Se insertó correctamente los usuarios";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarDatosUsuarios. ERROR : " + e.getMessage());
			throw e;
		}

	}
	
	public String insertarDatosDepartamento() {
		try {
			repoUtilitario.insertarDepartamento();
			return "Se insertó correctamente los Departamentos";
		} catch (Exception e) {
			return "Se insertó correctamente los Departamentos";

		}
	}

	public String insertarDatosProvincia() {
		try {
			repoUtilitario.insertarProvincia();
			return "Se insertó correctamente las Provincias";
		} catch (Exception e) {
			return "Se insertó correctamente las Provincias";

		}
	}

	public String insertarDatosDistrito() {
		try {
			repoUtilitario.insertarDistrito();
			return "Se insertó correctamente los Distritos";
		} catch (Exception e) {
			return "Se insertó correctamente los Distritos";
		}
	}
	
	public String insertarDatosNivelEducativo(List<NivelEducativo> obj) {
		try {
			repoNivEduRepo.saveAll(obj);
			return "Se insertó correctamente los niveles educativos";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarDatosNivelEducativo. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	public String insertarDatosGrado(List<Grado> obj) {
		try {
			repoGrado.saveAll(obj);
			return "Se insertó correctamente los grados";
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " insertarDatosGrado. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
