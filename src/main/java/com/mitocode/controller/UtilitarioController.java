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

import com.mitocode.model.Modulo;
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
			Modulo modEstudiante = this.CrearModulo("Gestion estudiante", 1, "icon-gestion-estudiante.svg", 1, "app.uiestu");
			Modulo modEmpleado = this.CrearModulo("Gestion empleado", 1, "icon-gestion-empleado.svg", 2, "app.uiempl");
			Modulo modColegio = this.CrearModulo("Gestion colegio", 1, "icon-gestion-colegio.svg", 3, "app.uicole");
			Modulo modConfiguracion = this.CrearModulo("Configuracion", 1, "icon-configuracion.svg", 4, "app.uiconf");
			tmp_modulo.add(modEstudiante);
			tmp_modulo.add(modColegio);
			tmp_modulo.add(modEmpleado);
			tmp_modulo.add(modConfiguracion);
			messages.add(service.insertarDatosModulo(tmp_modulo));

			List<Pagina> tmp_pagina = new ArrayList<>();
			Pagina ge = this.CrearPagina("Estudiante", 1, "icon-estudiantes.svg", 0, "-", "/gestionestudiante", modEstudiante);
			Pagina ga = this.CrearPagina("Apoderado", 1, "icon-apoderado.svg", 0, "-", "/gestionapoderado", modEstudiante);
			Pagina emp = this.CrearPagina("Empleado", 1, "icon-empleado.svg", 0, "-", "/gestionempleado", modEmpleado);
			Pagina depu = this.CrearPagina("Organigrama", 1, "icon-organigrama.svg", 0, "-", "/gestionorganigrama", modEmpleado);
			Pagina gc = this.CrearPagina("Colegio", 1, "icon-colegio.svg", 0, "-", "/gestioncolegio", modColegio);
			Pagina gs = this.CrearPagina("Sucursal", 1, "icon-sucursal.svg", 0, "-", "/gestionsucursal", modColegio);
			Pagina gsa = this.CrearPagina("Salon", 1, "icon-salon-clases.svg", 0, "-", "/gestionsalon", modColegio);
			Pagina gse = this.CrearPagina("Seccion", 1, "icon-seccion.svg", 0, "-", "/gestionseccion", modColegio);
			Pagina gcu = this.CrearPagina("Curso", 1, "icon-curso.svg", 0, "-", "/gestioncurso", modColegio);
			Pagina pa = this.CrearPagina("Parametros", 1, "icon-parametros.svg", 0, "-", "/gestionparametros", modConfiguracion);

			tmp_pagina.add(ge);
			tmp_pagina.add(ga);
			tmp_pagina.add(emp);
			tmp_pagina.add(depu);
			tmp_pagina.add(gc);
			tmp_pagina.add(gs);
			tmp_pagina.add(gsa);
			tmp_pagina.add(gse);
			tmp_pagina.add(gcu);
			tmp_pagina.add(pa);
			messages.add(service.insertarDatosPagina(tmp_pagina));

			List<Perfil> tmp_perfil = new ArrayList<>();
			Perfil role_admi = this.CrearPerfil(1, true, "ROLE_ADMIN");
			Perfil admi = this.CrearPerfil(1, true, "Administrador");
			Perfil est = this.CrearPerfil(1, true, "Estudiante");
			Perfil apo = this.CrearPerfil(1, true, "Apoderado");
			Perfil mae = this.CrearPerfil(1, true, "Maestro");
			tmp_perfil.add(role_admi);
			tmp_perfil.add(admi);
			tmp_perfil.add(est);
			tmp_perfil.add(apo);
			tmp_perfil.add(mae);
			messages.add(service.insertarDatosPerfil(tmp_perfil));

			List<Usuario> tmp_usuarios = new ArrayList<>();
			Usuario user1 = this.crearUsuario(true,
					"$2a$10$5lQfLdWrdOiZudh3cCNmbOz2TcU3DtgfjqCeFHvGS1HDBHSvlNdu6", "partner", role_admi);
			tmp_usuarios.add(user1);
			messages.add(service.insertarDatosUsuarios(tmp_usuarios));
			
			messages.add(service.insertarDatosPerfilesPaginas());
			messages.add(service.insertarDatosTipoDoc());
			messages.add(service.insertarDatosPais());
			messages.add(service.insertarDatosDepartamento());
			messages.add(service.insertarDatosProvincia());
			messages.add(service.insertarDatosDistrito());
			messages.add(service.insertarDatosTipoZona());
			messages.add(service.insertarDatosTipoPago());
			messages.add(service.insertarDatosBanco());

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

	private Usuario crearUsuario(Boolean estado, String pass, String username, Perfil perfil) {
		Usuario u = new Usuario();
		u.setEstado(estado);
		u.setUsername(username);
		u.setPassword(pass);
		u.setPerfil(perfil);
		return u;
	}
}
