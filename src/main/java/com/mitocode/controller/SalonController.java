package com.mitocode.controller;

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
import com.mitocode.dto.SalonDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;
import com.mitocode.service.SalonService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/salon")
public class SalonController {

	@Autowired
	SalonService service;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Sucursal sucursal) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsSalon = service.listarPorSucursal(sucursal);
			if (lsSalon != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarSalonOk);
				response.setAaData(lsSalon);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarSalonError);
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
			Salon salon = salonDTO.getSalon();
			salon.setSucursal(salonDTO.getSucursal());
			Salon resp = service.registrar(salon);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarSalonOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarSalonError);
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
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody Salon salon, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), salon);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Salon resp = service.modificar(salon);
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarSalonOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarSalonError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							salon);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idSalon}")
	public ResponseWrapper eliminar(@PathVariable("idSalon") Integer idSalon) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idSalon)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarSalonOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarSalonError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							idSalon);
		}
	}
}
