package com.mitocode.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Grado;
import com.mitocode.model.Modulo;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Pagina;
import com.mitocode.model.Perfil;
import com.mitocode.model.Usuario;
import com.mitocode.service.UtilitarioService;

@RestController
@RequestMapping("/api/utilitario")
public class UtilitarioController {

	@Autowired
	private UtilitarioService service;

	Map<String, List<String>> resp_BD = new HashMap<>();

	@PostMapping("/llenarBD")
	public ResponseEntity<?> insertarDatos1() throws Exception {
		try {
			List<String> messages = new ArrayList<>();
			List<Modulo> tmp_modulo = new ArrayList<>();
			Modulo modColegio = this.CrearModulo("Gestion Colegio", 1, "icon-gestion-colegio.svg", 1, "app.uicole");
			tmp_modulo.add(modColegio);
			messages.add(service.insertarDatosModulo(tmp_modulo));

			List<Pagina> tmp_pagina = new ArrayList<>();
			Pagina gc = this.CrearPagina("Colegio", 1, "icon-colegio.svg", 0, "-", "/gestioncolegio", modColegio);
			Pagina gs = this.CrearPagina("Sucursal", 1, "icon-sucursal.svg", 0, "-", "/gestionsucursal", modColegio);
			Pagina gsa = this.CrearPagina("Salon", 1, "icon-salon-clases.svg", 0, "-", "/gestionsalon", modColegio);
			Pagina gcu = this.CrearPagina("Curso", 1, "icon-curso.svg", 0, "-", "/gestioncurso", modColegio);

			tmp_pagina.add(gc);
			tmp_pagina.add(gs);
			tmp_pagina.add(gsa);
			tmp_pagina.add(gcu);
			messages.add(service.insertarDatosPagina(tmp_pagina));
			

			messages.add(service.insertarDatosDepartamento());
			messages.add(service.insertarDatosProvincia());
			messages.add(service.insertarDatosDistrito());

			List<Perfil> tmp_perfil = new ArrayList<>();
			Perfil role_admi = this.CrearPerfil(1, true, "ROLE_ADMIN");
			Perfil admi = this.CrearPerfil(1, true, "Administrador");
			Perfil trab = this.CrearPerfil(1, true, "Trabajador");
			tmp_perfil.add(role_admi);
			tmp_perfil.add(admi);
			tmp_perfil.add(trab);
			messages.add(service.insertarDatosPerfil(tmp_perfil));

			List<Usuario> tmp_usuarios = new ArrayList<>();
			Usuario user1 = this.crearUsuario("partnertech@gmail.com", true,
					"$2a$10$5lQfLdWrdOiZudh3cCNmbOz2TcU3DtgfjqCeFHvGS1HDBHSvlNdu6", "partner", role_admi);
			tmp_usuarios.add(user1);
			messages.add(service.insertarDatosUsuarios(tmp_usuarios));
			
			messages.add(service.insertarDatosPerfilesPaginas());

			resp_BD.put("mensaje", messages);

		} catch (Exception e) {

		}
		return new ResponseEntity<Map<String, List<String>>>(resp_BD, HttpStatus.OK);

	}

	private Modulo CrearModulo(String des, int estado, String icono, int orden, String raiz) {
		Modulo modulo = new Modulo();
		modulo.setDescripcion(des);
		modulo.setEstado(estado);
		modulo.setIcono(icono);
		modulo.setOrden(orden);
		modulo.setRaiz(raiz);

		return modulo;
	}

	private Pagina CrearPagina(String desc, int estado, String icono, int orden, String param, String url,
			Modulo modulo) {
		Pagina pagina = new Pagina();
		pagina.setDescripcion(desc);
		pagina.setEstado(estado);
		pagina.setIcono(icono);
		pagina.setOrden(orden);
		pagina.setParametros(param);
		pagina.setUrl(url);
		pagina.setModulo(modulo);

		return pagina;
	}

	private Perfil CrearPerfil(int ambito, boolean estado, String nombres) {
		Perfil p = new Perfil();
		p.setAmbito(ambito);
		p.setEstado(estado);
		p.setNombres(nombres);
		return p;
	}

	private Usuario crearUsuario(String email, Boolean estado, String pass, String username, Perfil perfil) {
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setEstado(estado);
		u.setUsername(username);
		u.setPassword(pass);
		u.setPerfil(perfil);
		return u;
	}
}
