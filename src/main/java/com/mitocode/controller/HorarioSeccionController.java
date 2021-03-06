package com.mitocode.controller;

import java.sql.Timestamp;
import java.util.Calendar;
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

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.dto.SeccionDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Curso;
import com.mitocode.model.HorarioMaestro;
import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.Parametro;
import com.mitocode.model.Seccion;
import com.mitocode.service.CursoService;
import com.mitocode.service.HorarioMaestroService;
import com.mitocode.service.HorarioSeccionService;
import com.mitocode.service.ParametroService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/horarioSeccion")
public class HorarioSeccionController {

	@Autowired
	HorarioSeccionService service;

	@Autowired
	HorarioMaestroService serviceHorarioMaestro;

	@Autowired
	CursoService serviveCurso;

	@Autowired
	ParametroService serviceParametro;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Seccion seccion) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsHorarioSeccion = service.listarPorSeccion(seccion);
			if (lsHorarioSeccion != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsHorarioSeccion);
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
	@PostMapping("/listarporSeccionYDia")
	public ResponseWrapper listarporSeccionYDia(@RequestBody SeccionDTO seccionDTO) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsHorarioSeccion = service.listarporSeccionYDia(seccionDTO.getSeccion().getIdSeccion(),
					seccionDTO.getDiaLaboral().getIdDiaLaboral());
			if (lsHorarioSeccion != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsHorarioSeccion);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarporSeccionYDia",
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

			Parametro parametro = serviceParametro.encontrarPorCodigoYColegio(Constantes.CODMINENTCUR,
					seccionDTO.getColegio());

			HorarioSeccion horarioSeccion = armarHorarioSeccion(seccionDTO, parametro);
			List<HorarioSeccion> existeHorarioSeccion = service.encontrarPorHoraInicioYFin(
					horarioSeccion.getSeccion().getIdSeccion(), horarioSeccion.getDiaLaboral().getIdDiaLaboral(),
					horarioSeccion.getHoraInicio(), horarioSeccion.getHoraFin());

			if (existeHorarioSeccion.size() == 0) {

				HorarioMaestro horarioMaestro = armarHorarioMaestro(horarioSeccion);
				List<HorarioMaestro> existeHorarioMaestro = serviceHorarioMaestro.encontrarPorHoraInicioYFin(
						horarioMaestro.getEmpleado().getIdEmpleado(), horarioMaestro.getDiaLaboral().getIdDiaLaboral(),
						horarioMaestro.getHoraInicio(), horarioMaestro.getHoraFin());

				if (existeHorarioMaestro.size() == 0) {
					HorarioMaestro respM = serviceHorarioMaestro.registrar(horarioMaestro);
					horarioSeccion.setIdHorarioMaestro(respM.getIdHorarioMaestro());
					HorarioSeccion resp = service.registrar(horarioSeccion);
					if (respM != null && resp != null) {
						response.setEstado(Constantes.valTransaccionOk);
						response.setMsg(Constantes.msgRegistrarHorarioSeccionOk);
						response.setDefaultObj(resp);
					} else {
						response.setEstado(Constantes.valTransaccionError);
						response.setMsg(Constantes.msgRegistrarHorarioSeccionError);
					}
				} else {
					response.setEstado(Constantes.valTransaccionYaExiste);
					response.setMsg(crearMensaje(existeHorarioMaestro.get(0)));
				}
			} else {
				response.setEstado(Constantes.valTransaccionYaExiste);
				response.setMsg(crearMensaje(existeHorarioSeccion.get(0)));
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					seccionDTO);
		}
	}
	
	private HorarioSeccion armarHorarioSeccion(SeccionDTO dto, Parametro parametro) {
		HorarioSeccion horarioSeccion = dto.getHorarioSeccion();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(horarioSeccion.getHoraInicio());
		calendar.set(2020, 0, 1);
		int minCurso = Integer.parseInt(parametro.getValor());

		calendar.add(Calendar.MINUTE, minCurso);

		horarioSeccion.setHoraInicio(new Timestamp(calendar.getTimeInMillis()));

		calendar.add(Calendar.HOUR, horarioSeccion.getHoraDuracion());
		calendar.add(Calendar.MINUTE, horarioSeccion.getMinutoDuracion() - minCurso);

		horarioSeccion.setHoraFin(new Timestamp(calendar.getTimeInMillis()));
		horarioSeccion.setSeccion(dto.getSeccion());
		horarioSeccion.setCurso(dto.getCurso());
		horarioSeccion.setDiaLaboral(dto.getDiaLaboral());

		return horarioSeccion;
	}

	private HorarioMaestro armarHorarioMaestro(HorarioSeccion horarioSeccion) {
		Curso curso = serviveCurso.encontrar(horarioSeccion.getCurso().getIdCurso());
		HorarioMaestro horarioMaestro = new HorarioMaestro();
		horarioMaestro.setHoraDuracion(horarioSeccion.getHoraDuracion());
		horarioMaestro.setMinutoDuracion(horarioSeccion.getMinutoDuracion());
		horarioMaestro.setHoraInicio(horarioSeccion.getHoraInicio());
		horarioMaestro.setHoraFin(horarioSeccion.getHoraFin());
		horarioMaestro.setDiaLaboral(horarioSeccion.getDiaLaboral());
		horarioMaestro.setSalon(horarioSeccion.getSeccion().getSalon());
		horarioMaestro.setCurso(curso);
		horarioMaestro.setEmpleado(curso.getEmpleado());
		return horarioMaestro;
	}

	private String crearMensaje(HorarioSeccion hs) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		String horaInicio = dateFormat.format(hs.getHoraInicio());
		String horaFin = dateFormat.format(hs.getHoraFin());
		return "Seleccione un horario diferente, debido a que el curso " + hs.getCurso().getDescripcion()
				+ " esta ocupando ese horario, inicia " + horaInicio + " y finaliza " + horaFin;
	}

	private String crearMensaje(HorarioMaestro hm) {
		String nombreCompleto = hm.getEmpleado().getApePaterno() + " " + hm.getEmpleado().getApeMaterno() + " "
				+ hm.getEmpleado().getNombres();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
		String horaInicio = dateFormat.format(hm.getHoraInicio());
		String horaFin = dateFormat.format(hm.getHoraFin());
		return "Seleccione un horario diferente, debido a que el maestro " + nombreCompleto + " esta dictando el curso "
				+ hm.getCurso().getDescripcion() + " para otra seccion en este horario, el cual inicia " + horaInicio
				+ " y finaliza " + horaFin;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idHorarioSeccion}")
	public ResponseWrapper eliminar(@PathVariable("idHorarioSeccion") Integer idHorarioSeccion) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			HorarioSeccion horarioSeccion = service.encontrar(idHorarioSeccion);
			if (!service.eliminar(idHorarioSeccion)) {
				serviceHorarioMaestro.eliminar(horarioSeccion.getIdHorarioMaestro());
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarHorarioSeccionOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarHorarioSeccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idHorarioSeccion);
		}
	}
}
