package com.mitocode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.service.BancoService;
import com.mitocode.service.TipoPagoService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

	@Autowired
	BancoService service;

	@Autowired
	TipoPagoService serviceTipoPago;

	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsBanco = service.listar();
			if (lsBanco != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsBanco);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarBancos. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}

	@GetMapping("/listarTipoPago")
	public ResponseWrapper listarTipoPago() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsTipoPago = serviceTipoPago.listar();
			if (lsTipoPago != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsTipoPago);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarTipoPago. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}
}
