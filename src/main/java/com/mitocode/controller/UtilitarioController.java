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
//			List<Modulo> tmp_modulo = new ArrayList<>();
//			Modulo modTrabajador = this.CrearModulo("Trabajador", 1, "mdi mdi-worker", 2, "app.uitrab");
//			Modulo modUsuario = this.CrearModulo("Administracion", 1, "mdi mdi-account", 1, "app.uiadm");
//			Modulo modPlanillas = this.CrearModulo("Planillas", 1, "mdi mdi-clipboard-text", 3, "app.uiplan");
//			Modulo modConfiguracion = this.CrearModulo("Configuracion", 1, "mdi mdi-settings", 5, "app.uiconf");
//			Modulo modContabilidad = this.CrearModulo("Contabilidad", 1, "mdi mdi-pen", 4, "app.uicont");
//			tmp_modulo.add(modUsuario);
//			tmp_modulo.add(modTrabajador);
//			tmp_modulo.add(modPlanillas);
//			tmp_modulo.add(modConfiguracion);
//			tmp_modulo.add(modContabilidad);
//			messages.add(service.insertarDatosModulo(tmp_modulo));
//
//			List<Pagina> tmp_pagina = new ArrayList<>();
//			Pagina gu = this.CrearPagina("Gestion Usuarios", 1, "mdi mdi-shield", 0, "-", "/gestionusuarios",
//					modUsuario);
//			Pagina gh = this.CrearPagina("Horarios", 1, "mdi mdi-calendar-clock", 0, "-", "/horarios",
//					modConfiguracion);
//			Pagina gt = this.CrearPagina("Gestion Trabajador", 1, "mdi mdi-worker", 0, "-", "/gestiontrabajador",
//					modTrabajador);
//			Pagina dh = this.CrearPagina("Derecho Habientes", 1, "mdi mdi-heart-box", 0, "-", "/derechohabientes",
//					modTrabajador);
//			Pagina gp = this.CrearPagina("Generar Planillas", 1, "mdi mdi-newspaper", 0, "-", "/generarplanillas",
//					modPlanillas);
//			Pagina as = this.CrearPagina("Adelanto Sueldo", 1, "mdi mdi-cash-multiple", 0, "-", "/adelantosueldo",
//					modPlanillas);
//			Pagina p = this.CrearPagina("Parametros", 1, "mdi mdi-wrench", 0, "-", "/parametros", modConfiguracion);
//			Pagina afp = this.CrearPagina("AFP", 1, "mdi mdi-hospital", 0, "-", "/afp", modConfiguracion);
//			Pagina cat = this.CrearPagina("Categoria", 1, "mdi mdi-seal", 0, "-", "/categoria", modConfiguracion);
//			Pagina car = this.CrearPagina("Cargo", 1, "mdi mdi-trophy-award", 0, "-", "/cargo", modConfiguracion);
//			Pagina emp = this.CrearPagina("Empresa", 1, "mdi mdi-factory", 0, "-", "/empresa", modConfiguracion);
//			Pagina aym = this.CrearPagina("Año y Mes", 1, "mdi mdi-calendar-text", 0, "-", "/anual", modConfiguracion);
//			Pagina cc = this.CrearPagina("Centro Costo", 1, "mdi mdi-archive", 0, "-", "/centrocostos",
//					modConfiguracion);
//			Pagina sctr = this.CrearPagina("S.C.T.R.", 1, "mdi mdi-bell-plus", 0, "-", "/sctr", modConfiguracion);
//			Pagina eps_pag = this.CrearPagina("E.P.S.", 1, "mdi mdi-hospital-building", 0, "-", "/eps",
//					modConfiguracion);
//			Pagina permi = this.CrearPagina("Permisos", 1, "mdi mdi-wheelchair-accessibility", 0, "-", "/permisos",
//					modConfiguracion);
//			Pagina remu = this.CrearPagina("Remuneraciones", 1, "mdi mdi-coin", 0, "-", "/remuneraciones",
//					modConfiguracion);
//			Pagina dsct = this.CrearPagina("Descuentos", 1, "mdi mdi-square-inc-cash", 0, "-", "/descuentos",
//					modConfiguracion);
//			Pagina tipplan = this.CrearPagina("Tipo Planilla", 1, "mdi mdi-clipboard-account", 0, "-", "/tipoplanilla",
//					modConfiguracion);
//			Pagina dptEmpr = this.CrearPagina("Departamento Empresa", 1, "mdi mdi-clipboard-alert", 0, "-",
//					"/departamentoempresa", modConfiguracion);
//			Pagina areaEmpr = this.CrearPagina("Area Empresa", 1, "mdi mdi-clipboard-alert", 0, "-",
//					"/areadepartamentoempresa", modConfiguracion);
//			Pagina puesto = this.CrearPagina("Puesto", 1, "mdi mdi-clipboard-alert", 0, "-", "/puestoareaempresa",
//					modConfiguracion);
//			Pagina asist = this.CrearPagina("Control Asistencia", 1, "mdi mdi-clock-fast", 0, "-", "/controlasistencia",
//					modTrabajador);
//			Pagina vacas = this.CrearPagina("Gestion Vacaciones", 1, "mdi mdi-airplane", 0, "-", "/vacaciones",
//					modPlanillas);
//			Pagina conCue = this.CrearPagina("Conceptos y cuentas", 1, "mdi mdi-account-box", 0, "-", "/conceptoscuentas",
//					modContabilidad);
//			tmp_pagina.add(gu);
//			tmp_pagina.add(gh);
//			tmp_pagina.add(gt);
//			tmp_pagina.add(dh);
//			tmp_pagina.add(gp);
//			tmp_pagina.add(as);
//			tmp_pagina.add(p);
//			tmp_pagina.add(afp);
//			tmp_pagina.add(cat);
//			tmp_pagina.add(car);
//			tmp_pagina.add(emp);
//			tmp_pagina.add(aym);
//			tmp_pagina.add(cc);
//			tmp_pagina.add(sctr);
//			tmp_pagina.add(eps_pag);
//			tmp_pagina.add(permi);
//			tmp_pagina.add(remu);
//			tmp_pagina.add(dsct);
//			tmp_pagina.add(tipplan);
//			tmp_pagina.add(dptEmpr);
//			tmp_pagina.add(areaEmpr);
//			tmp_pagina.add(puesto);
//			tmp_pagina.add(asist);
//			tmp_pagina.add(vacas);
//			tmp_pagina.add(conCue);
//			messages.add(service.insertarDatosPagina(tmp_pagina));
			

			messages.add(service.insertarDatosDepartamento());
			messages.add(service.insertarDatosProvincia());
			messages.add(service.insertarDatosDistrito());
//
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
			
			List<NivelEducativo> tmp_nivelEducativo = new ArrayList<NivelEducativo>();
			NivelEducativo ni = crearNivelEducativo("Nivel Inicial");
			NivelEducativo np = crearNivelEducativo("Nivel Primaria");
			NivelEducativo ns = crearNivelEducativo("Nivel Secundaria");
			tmp_nivelEducativo.add(ni);
			tmp_nivelEducativo.add(np);
			tmp_nivelEducativo.add(ns);
			messages.add(service.insertarDatosNivelEducativo(tmp_nivelEducativo));
			
			List<Grado> tmp_grado = new ArrayList<Grado>();
			Grado uno = crearGrado(ni, "3 años");
			Grado dos = crearGrado(ni, "4 años");
			Grado tres = crearGrado(ni, "5 años");
			Grado ppri = crearGrado(np, "Primero");
			Grado pseg = crearGrado(np, "Segundo");
			Grado pter = crearGrado(np, "Tercero");
			Grado pcua = crearGrado(np, "Cuarto");
			Grado pqui = crearGrado(np, "Quinto");
			Grado psex = crearGrado(np, "Sexto");
			Grado spri = crearGrado(ns, "Primero");
			Grado sseg = crearGrado(ns, "Segundo");
			Grado ster = crearGrado(ns, "Tercero");
			Grado scua = crearGrado(ns, "Cuarto");
			Grado squi = crearGrado(ns, "Quinto");
			tmp_grado.add(uno);
			tmp_grado.add(dos);
			tmp_grado.add(tres);
			tmp_grado.add(ppri);
			tmp_grado.add(pseg);
			tmp_grado.add(pter);
			tmp_grado.add(pcua);
			tmp_grado.add(pqui);
			tmp_grado.add(psex);
			tmp_grado.add(spri);
			tmp_grado.add(sseg);
			tmp_grado.add(ster);
			tmp_grado.add(scua);
			tmp_grado.add(squi);
			messages.add(service.insertarDatosGrado(tmp_grado));

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
	
	private NivelEducativo crearNivelEducativo(String descripcion) {
		NivelEducativo ne = new NivelEducativo();
		ne.setDescripcion(descripcion);
		return ne;
	}
	
	private Grado crearGrado(NivelEducativo nivelEducativo, String descripcion) {
		Grado g = new Grado();
		g.setNivelEducativo(nivelEducativo);
		g.setDescripcion(descripcion);
		return g;
	}
}
