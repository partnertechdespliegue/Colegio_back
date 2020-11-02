package com.mitocode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.service.TipoDocService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/tipoDoc")
public class TipoDocController {
	
	@Autowired
	TipoDocService service;

	
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lstdc = service.listar();
			if (lstdc != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lstdc);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listartipoDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}
	
	@GetMapping("/listarSinRuc")
	public ResponseWrapper listarSinRuc() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lstdc = service.listarSinRuc();
			if (lstdc != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lstdc);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listartipoDocumento. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarSinRuc",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}
}
