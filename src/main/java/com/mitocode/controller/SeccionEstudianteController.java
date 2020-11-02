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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.dto.SeccionDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Seccion;
import com.mitocode.model.SeccionEstudiante;
import com.mitocode.model.Sucursal;
import com.mitocode.service.EstudianteService;
import com.mitocode.service.SeccionEstudianteService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/seccionEstudiante")
public class SeccionEstudianteController {

	@Autowired
	SeccionEstudianteService service;

	@Autowired
	EstudianteService serviceEstudiante;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarAsignados")
	public ResponseWrapper listarAsignados(@RequestBody Seccion seccion) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsSeccionEstudiante = service.listarPorSeccion(seccion);
			if (lsSeccionEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsSeccionEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					seccion);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarSinAsignar")
	public ResponseWrapper listarSinAsignar(@RequestBody SeccionDTO seccionDTO) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			Sucursal sucursal = seccionDTO.getSucursal();
			NivelEducativo nivelEducativo = seccionDTO.getSeccion().getNivelEducativo();
			Grado grado = seccionDTO.getSeccion().getGrado();
			List<Estudiante> lsEstudiante = serviceEstudiante.listarPorSucursalNivEducaGrago(sucursal, nivelEducativo, grado);
			List<SeccionEstudiante> lsSeccionEstudiante = service.listarPorColegioNivEducaGrado(sucursal.getColegio().getIdColegio(), nivelEducativo.getIdNivelEducativo(), grado.getIdGrado());
			List<Estudiante> lsEstudianteSinAsignar = new ArrayList<Estudiante>();

			for (Estudiante estudiante : lsEstudiante) {
				boolean existe = false;
				for (SeccionEstudiante seccionEstudiante : lsSeccionEstudiante) {
					if (seccionEstudiante.getEstudiante().getIdEstudiante() == estudiante.getIdEstudiante()) {
						existe = true;
					}
				}
				if (!existe) {
					lsEstudianteSinAsignar.add(estudiante);
				}
			}

			response.setEstado(Constantes.valTransaccionOk);
			response.setAaData(lsEstudianteSinAsignar);

			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							seccionDTO);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody SeccionDTO seccionDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), seccionDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			
			List<SeccionEstudiante> lsSeccionEstudiante = new ArrayList<SeccionEstudiante>();
			Seccion seccion = seccionDTO.getSeccion();
			for (Estudiante e : seccionDTO.getLsEstudiante()) {
				lsSeccionEstudiante.add(new SeccionEstudiante(seccion, e));
			}
			
			List<SeccionEstudiante> resp = service.registrarLista(lsSeccionEstudiante);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgAsignarEstudianteSeccionOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgAsignarEstudianteSeccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), seccionDTO);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/eliminar")
	public ResponseWrapper eliminar(@RequestBody List<SeccionEstudiante> lsSeccionEstudiante) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			
			for (SeccionEstudiante seccionEstudiante : lsSeccionEstudiante) {
				service.eliminar(seccionEstudiante.getIdSeccionEstudiante());
			}
			response.setEstado(Constantes.valTransaccionOk);
			response.setMsg(Constantes.msgEliminarEstudianteSeccionOk);
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							lsSeccionEstudiante);
		}
	}
}
