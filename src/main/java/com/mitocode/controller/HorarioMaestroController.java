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

import com.mitocode.dto.EmpleadoDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.service.HorarioMaestroService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/horarioMaestro")
public class HorarioMaestroController {
	
	@Autowired
	HorarioMaestroService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarporEmpleadoYDia")
	public ResponseWrapper listarporEmpleadoYDia(@RequestBody EmpleadoDTO empleadoDTO) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsHorarioMaestro = service.listarporEmpleadoYDia(empleadoDTO.getEmpleado().getIdEmpleado(), empleadoDTO.getDiaLaboral().getIdDiaLaboral());
			if (lsHorarioMaestro != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsHorarioMaestro);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarporEmpleadoYDia. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarporEmpleadoYDia",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							empleadoDTO);
		}
	}

}
