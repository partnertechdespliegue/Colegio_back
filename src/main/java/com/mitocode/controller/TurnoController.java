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
import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;
import com.mitocode.service.RecesoService;
import com.mitocode.service.TurnoService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {

	@Autowired
	TurnoService service;

	@Autowired
	RecesoService serviceReceso;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Sucursal sucursal) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsTurno = service.listarPorSucursal(sucursal);
			if (lsTurno != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarTurnoOk);
				response.setAaData(lsTurno);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarTurnoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					sucursal);
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
				Turno turno = turnoDTO.getTurno();
				Sucursal sucursal = turnoDTO.getSucursal();
				turno.setSucursal(sucursal);
				Turno resp = service.registrar(turno);
				if (resp != null) {
					if (turnoDTO.getLsReceso().size() > 0) {
						for (Receso receso : turnoDTO.getLsReceso()) {
							receso.setTurno(resp);
							serviceReceso.registrar(receso);
						}
					}
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarTurnoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarTurnoError);
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
		Sucursal sucursal = turnoDTO.getSucursal();

		Calendar horaInicioSucursal = Calendar.getInstance();
		horaInicioSucursal.setTime(sucursal.getHoraInicioAtencion());

		int horaIniSuc = horaInicioSucursal.get(Calendar.HOUR_OF_DAY);
		int minIniSuc = horaInicioSucursal.get(Calendar.MINUTE);

		Calendar horaFinSucursal = Calendar.getInstance();
		horaFinSucursal.setTime(sucursal.getHoraFinAtencion());

		int horaFinSuc = horaFinSucursal.get(Calendar.HOUR_OF_DAY);
		int minFinSuc = horaFinSucursal.get(Calendar.MINUTE);

		Calendar horaInicioTurno = Calendar.getInstance();
		horaInicioTurno.setTime(turno.getHoraInicio());

		int horaIniTur = horaInicioTurno.get(Calendar.HOUR_OF_DAY);
		int minIniTur = horaInicioTurno.get(Calendar.MINUTE);

		Calendar horaFinTurno = Calendar.getInstance();
		horaFinTurno.setTime(turno.getHoraFin());

		int horaFinTur = horaFinTurno.get(Calendar.HOUR_OF_DAY);
		int minFinTur = horaFinTurno.get(Calendar.MINUTE);

		if (horaIniSuc > horaIniTur) {
			return false;
		}

		if (horaIniSuc == horaIniTur) {
			if (minIniSuc > minIniTur) {
				return false;
			}
		}

		if (horaFinSuc < horaFinTur) {
			return false;
		}

		if (horaFinSuc == horaFinTur) {
			if (minFinSuc < minFinTur) {
				return false;
			}
		}

		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody Turno turno, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), turno);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Turno resp = service.modificar(turno);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarTurnoOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarTurnoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					turno);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idTurno}")
	public ResponseWrapper eliminar(@PathVariable("idTurno") Integer idTurno) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idTurno)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarTurnoOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarTurnoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idTurno);
		}
	}

}
