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
import com.mitocode.dto.SalonDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Parametro;
import com.mitocode.model.Salon;
import com.mitocode.service.HorarioSalonService;
import com.mitocode.service.ParametroService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/horarioSalon")
public class HorarioSalonController {

	@Autowired
	HorarioSalonService service;
	
	@Autowired
	ParametroService serviceParametro;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Salon salon) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsHorarioSalon = service.listarPorSalon(salon);
			if (lsHorarioSalon != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarSucursalOk);
				response.setAaData(lsHorarioSalon);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarSucursalError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					salon);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarporSalonYDia")
	public ResponseWrapper listarporSalonYDia(@RequestBody SalonDTO salonDTO) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsHorarioSalon = service.listarporSalonYDia(salonDTO.getSalon().getIdSalon(), salonDTO.getDiaLaboral().getIdDiaLaboral());
			if (lsHorarioSalon != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarSucursalOk);
				response.setAaData(lsHorarioSalon);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarSucursalError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarporSalonYDia",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							salonDTO);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody SalonDTO salonDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), salonDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();

			Parametro parametro = serviceParametro.encontrarPorCodigoYColegio(Constantes.CODMINENTCUR, salonDTO.getColegio());
			
			HorarioSalon horarioSalon = salonDTO.getHorarioSalon();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(horarioSalon.getHoraInicio());
			calendar.set(2020, 0, 1);
			int minCurso = Integer.parseInt(parametro.getValor());
			
			calendar.add(Calendar.MINUTE, minCurso);

			horarioSalon.setHoraInicio(new Timestamp(calendar.getTimeInMillis()));

			calendar.add(Calendar.HOUR, horarioSalon.getHoraDuracion());
			calendar.add(Calendar.MINUTE, horarioSalon.getMinutoDuracion() - minCurso);

			horarioSalon.setHoraFin(new Timestamp(calendar.getTimeInMillis()));

			horarioSalon.setSalon(salonDTO.getSalon());
			horarioSalon.setCurso(salonDTO.getCurso());
			horarioSalon.setDiaLaboral(salonDTO.getDiaLaboral());

			List<HorarioSalon> existe = service.encontrarPorHoraInicioYFin(horarioSalon.getDiaLaboral().getIdDiaLaboral(),
					horarioSalon.getHoraInicio(), horarioSalon.getHoraFin());

			if (existe.size() == 0) {
				HorarioSalon resp = service.registrar(horarioSalon);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarSucursalOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarSucursalError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionYaExiste);
				response.setMsg(crearMensaje(existe.get(0)));
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					salonDTO);
		}
	}

	private String crearMensaje(HorarioSalon hs) {		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");  
		String horaInicio = dateFormat.format(hs.getHoraInicio());
		String horaFin = dateFormat.format(hs.getHoraFin());
		return "Seleccione un horario diferente, debido a que el curso " + hs.getCurso().getDescripcion() + " esta ocupando ese horario, inicia " + horaInicio + " y finaliza " + horaFin;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idHorarioSalon}")
	public ResponseWrapper eliminar(@PathVariable("idHorarioSalon") Integer idHorarioSalon) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idHorarioSalon)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarSucursalOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarSucursalError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idHorarioSalon);
		}
	}

}
