package com.mitocode.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.icu.text.SimpleDateFormat;
import com.mitocode.dto.AsistenciaDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.AsistenciaEstudiante;
import com.mitocode.model.Curso;
import com.mitocode.model.Estudiante;
import com.mitocode.model.HorarioMaestro;
import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.Seccion;
import com.mitocode.model.SeccionEstudiante;
import com.mitocode.service.AsistenciaEstudianteService;
import com.mitocode.service.EstudianteService;
import com.mitocode.service.HorarioSeccionService;
import com.mitocode.service.SeccionEstudianteService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/asistenciaEstudiante")
public class AsistenciaEstudianteController {

	@Autowired
	AsistenciaEstudianteService service;

	@Autowired
	EstudianteService serviceEstudiante;

	@Autowired
	SeccionEstudianteService serviceSeccionEstudiante;

	@Autowired
	HorarioSeccionService serviceHorarioSeccion;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarAsistenciaEstudiante")
	public ResponseWrapper listarAsistenciaEstudiante(@RequestBody HorarioMaestro horarioMaestro) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();

			HorarioSeccion horarioSeccion = serviceHorarioSeccion
					.encontrarPorIdHorarioMaestro(horarioMaestro.getIdHorarioMaestro());
			List<SeccionEstudiante> lsSeccionEstudiante = serviceSeccionEstudiante
					.listarPorSeccion(horarioSeccion.getSeccion());
			List<AsistenciaEstudiante> lsAsistenciaEstudiante = new ArrayList<AsistenciaEstudiante>();

			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = sm.parse(sm.format(new Date()));
			Timestamp hoy = new Timestamp(dt.getTime());

			for (SeccionEstudiante se : lsSeccionEstudiante) {
				lsAsistenciaEstudiante.add(new AsistenciaEstudiante(se.getEstudiante(), horarioSeccion, hoy));
			}

			if (lsAsistenciaEstudiante.size() > 0) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAsistenciaEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}

			return response;
		} catch (Exception e) {
			System.out.println(
					this.getClass().getSimpleName() + " listarAsistenciaEstudiante. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarAsistenciaEstudiante",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					horarioMaestro);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorHorarioSeccion")
	public ResponseWrapper listarPorHorarioSeccion(@RequestBody HorarioSeccion horarioSeccion) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsAsistenciaEstudiante = service.listarPorHorarioSeccion(horarioSeccion);
			if (lsAsistenciaEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAsistenciaEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPorHorarioSeccion. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorHorarioSeccion",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					horarioSeccion);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorSeccion")
	public ResponseWrapper listarPorSeccion(@RequestBody Seccion seccion) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsAsistenciaEstudiante = service.listarPorSeccion(seccion.getIdSeccion());
			if (lsAsistenciaEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAsistenciaEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPorSeccion. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorSeccion",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					seccion);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorCurso")
	public ResponseWrapper listarPorCurso(@RequestBody Curso curso) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsAsistenciaEstudiante = service.listarPorCurso(curso.getIdCurso());
			if (lsAsistenciaEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsAsistenciaEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPorCurso. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorCurso",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					curso);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrarMasivo")
	public ResponseWrapper registrarMasivo(@RequestBody List<AsistenciaEstudiante> lsAsistenciaEstudiante,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), lsAsistenciaEstudiante);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			List resp = service.registrarMasivo(lsAsistenciaEstudiante);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarAsistenciaEstudianteOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarAsistenciaEstudianteError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrarMasivo. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrarMasivo",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					lsAsistenciaEstudiante);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody AsistenciaDTO asistenciaDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), asistenciaDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			AsistenciaEstudiante asistenciaEstudiante = asistenciaDTO.getAsistenciaEstudiante();
			asistenciaEstudiante.setTipoAsistencia(asistenciaDTO.getTipoAsistencia());
			asistenciaEstudiante.setEstudiante(asistenciaDTO.getEstudiante());
			asistenciaEstudiante.setHorarioSeccion(asistenciaDTO.getHorarioSeccion());
			asistenciaEstudiante.setFechaAsistencia(new Timestamp(new Date().getTime()));
			AsistenciaEstudiante resp = service.registrar(asistenciaEstudiante);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarAsistenciaEstudianteOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarAsistenciaEstudianteError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					asistenciaDTO);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody AsistenciaDTO asistenciaDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), asistenciaDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			AsistenciaEstudiante asistenciaEstudiante = asistenciaDTO.getAsistenciaEstudiante();
			asistenciaEstudiante.setTipoAsistencia(asistenciaDTO.getTipoAsistencia());
			asistenciaEstudiante.setEstudiante(asistenciaDTO.getEstudiante());
			asistenciaEstudiante.setHorarioSeccion(asistenciaDTO.getHorarioSeccion());
			AsistenciaEstudiante resp = service.registrar(asistenciaEstudiante);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarAsistenciaEstudianteOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarAsistenciaEstudianteError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					asistenciaDTO);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idAsistencia}")
	public ResponseWrapper eliminar(@PathVariable("idAsistencia") Integer idAsistencia) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idAsistencia)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarAsistenciaEstudianteOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarAsistenciaEstudianteError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idAsistencia);
		}
	}
}
