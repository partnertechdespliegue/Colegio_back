package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Contrato;
import com.mitocode.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

	@Autowired
	EmpleadoService service;
	
	@Autowired
	Contrato serviceContrato;
}
