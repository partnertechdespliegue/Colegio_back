package com.mitocode.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Colegio;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Parametro;
import com.mitocode.service.ColegioService;
import com.mitocode.service.GradoService;
import com.mitocode.service.NivelEducativoService;
import com.mitocode.service.ParametroService;
import com.mitocode.service.SucursalService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/colegio")
public class ColegioController {

	@Autowired
	ColegioService service;
	
	@Autowired
	SucursalService serviceSucursal;
	
	@Autowired
	ParametroService serviceParametro;
	
	@Autowired
	NivelEducativoService serviceNivEduc;
	
	@Autowired
	GradoService serviceGrado;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsColegio = service.listar();
			if (lsColegio != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarColegioOk);
				response.setAaData(lsColegio);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarColegioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), null);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody Colegio colegio, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), colegio);
		}
		
		try {
			ResponseWrapper response = new ResponseWrapper();
			Colegio resp = service.registrar(colegio);
			if (resp != null) {
//				if (colegioDTO.getLsSucursal().size() > 0 ) {
//					for (Sucursal sucursal : colegioDTO.getLsSucursal()) {
//						sucursal.setColegio(resp);
//						serviceSucursal.registrar(sucursal);
//					}
//				}
				registrarNivelEducativoyGrado(resp);
				registrarParametro(resp);
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarColegioOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarColegioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), colegio);
		}
	}
	
	private void registrarNivelEducativoyGrado(Colegio colegio) {
		List<NivelEducativo> lsNivEduc = new ArrayList<NivelEducativo>();
		NivelEducativo ni = crearNivelEducativo("Nivel Inicial", colegio);
		NivelEducativo np = crearNivelEducativo("Nivel Primaria", colegio);
		NivelEducativo ns = crearNivelEducativo("Nivel Secundaria", colegio);
		lsNivEduc.add(ni);
		lsNivEduc.add(np);
		lsNivEduc.add(ns);
		serviceNivEduc.registrarList(lsNivEduc);
		
		List<Grado> lsGrado = new ArrayList<Grado>();
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
		lsGrado.add(uno);
		lsGrado.add(dos);
		lsGrado.add(tres);
		lsGrado.add(ppri);
		lsGrado.add(pseg);
		lsGrado.add(pter);
		lsGrado.add(pcua);
		lsGrado.add(pqui);
		lsGrado.add(psex);
		lsGrado.add(spri);
		lsGrado.add(sseg);
		lsGrado.add(ster);
		lsGrado.add(scua);
		lsGrado.add(squi);
		serviceGrado.registrarList(lsGrado);
	}
	
	private NivelEducativo crearNivelEducativo(String descripcion, Colegio colegio) {
		NivelEducativo ne = new NivelEducativo();
		ne.setDescripcion(descripcion);
		ne.setColegio(colegio);
		return ne;
	}
	
	private Grado crearGrado(NivelEducativo nivelEducativo, String descripcion) {
		Grado g = new Grado();
		g.setNivelEducativo(nivelEducativo);
		g.setDescripcion(descripcion);
		return g;
	}

	private void registrarParametro(Colegio resp) {
		Parametro parametro = new Parametro();
		parametro.setCodigo(Constantes.CODMINENTCUR);
		parametro.setDescripcion("Minutos entre cursos");
		parametro.setValor("5");
		parametro.setEstado(Constantes.ConsActivo);
		parametro.setColegio(resp);
		serviceParametro.registrar(parametro);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody Colegio colegio, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), colegio);
		}
		
		try {
			ResponseWrapper response = new ResponseWrapper();
			Colegio resp = service.modificar(colegio);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarColegioOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarColegioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), colegio);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idColegio}")
	public ResponseWrapper eliminar(@PathVariable("idColegio") Integer idColegio) throws Exception {
		
		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idColegio)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarColegioOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarColegioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), idColegio);
		}
	}

}
