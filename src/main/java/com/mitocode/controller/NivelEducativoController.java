package com.mitocode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Colegio;
import com.mitocode.service.NivelEducativoService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/nivelEducativo")
public class NivelEducativoController {
	
	@Autowired
	NivelEducativoService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Colegio colegio) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsNivelEducativo = service.listarPorColegio(colegio);
			if (lsNivelEducativo != null) {
				response.setEstado(Constantes.valTransaccionOk);
//				response.setMsg(Constantes.msgListarSucursalOk);
				response.setAaData(lsNivelEducativo);
			} else {
				response.setEstado(Constantes.valTransaccionError);
//				response.setMsg(Constantes.msgListarSucursalError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), colegio);
		}
	}

}
