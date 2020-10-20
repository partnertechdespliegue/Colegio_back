package com.mitocode.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.dto.TurnoDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Receso;
import com.mitocode.model.Turno;
import com.mitocode.service.RecesoService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/receso")
public class RecesoController {
	
	@Autowired
	RecesoService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Turno turno) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsReceso = service.listarPorTurno(turno);
			if (lsReceso != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarRecesoOk);
				response.setAaData(lsReceso);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarRecesoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							turno);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody TurnoDTO turnoDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), turnoDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			if (validarHora(turnoDTO)) {
				Receso receso = turnoDTO.getReceso();
				receso.setTurno(turnoDTO.getTurno());
				Receso resp = service.registrar(receso);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarRecesoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarRecesoError);
				}
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					turnoDTO);
		}
	}
	
	private boolean validarHora(TurnoDTO turnoDTO) {

		Turno turno = turnoDTO.getTurno();
		Receso receso = turnoDTO.getReceso();
		
		Calendar horaInicioTurno = Calendar.getInstance();
		horaInicioTurno.setTime(turno.getHoraInicio());

		int horaIniTur = horaInicioTurno.get(Calendar.HOUR_OF_DAY);
		int minIniTur = horaInicioTurno.get(Calendar.MINUTE);

		Calendar horaFinTurno = Calendar.getInstance();
		horaFinTurno.setTime(turno.getHoraFin());

		int horaFinTur = horaFinTurno.get(Calendar.HOUR_OF_DAY);
		int minFinTur = horaFinTurno.get(Calendar.MINUTE);

		Calendar horaInicioReceso = Calendar.getInstance();
		horaInicioReceso.setTime(receso.getHoraInicio());

		int horaIniRec = horaInicioReceso.get(Calendar.HOUR_OF_DAY);
		int minIniRec = horaInicioReceso.get(Calendar.MINUTE);

		Calendar horaFinReceso = Calendar.getInstance();
		horaFinReceso.setTime(receso.getHoraFin());

		int horaFinRec = horaFinReceso.get(Calendar.HOUR_OF_DAY);
		int minFinRec = horaFinReceso.get(Calendar.MINUTE);
	

		if (horaIniTur > horaIniRec) {
			return false;
		}

		if (horaIniTur == horaIniRec) {
			if (minIniTur > minIniRec) {
				return false;
			}
		}

		if (horaFinTur < horaFinRec) {
			return false;
		}

		if (horaFinTur == horaFinRec) {
			if (minFinTur < minFinRec) {
				return false;
			}
		}

		return true;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody Receso receso, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), receso);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Receso resp = service.modificar(receso);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarRecesoOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarRecesoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							receso);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idReceso}")
	public ResponseWrapper eliminar(@PathVariable("idReceso") Integer idReceso) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idReceso)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarRecesoOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarRecesoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							idReceso);
		}
	}

}
