package com.mitocode.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Departamento;
import com.mitocode.model.Provincia;
import com.mitocode.service.DepartamentoService;
import com.mitocode.service.DistritoService;
import com.mitocode.service.PaisService;
import com.mitocode.service.ProvinciaService;
import com.mitocode.service.TipoZonaService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/ubigeo")
public class UbigeoController {
	
	@Autowired
	DepartamentoService serviceDepartamento;
	
	@Autowired
	ProvinciaService serviceProvincia;
	
	@Autowired
	DistritoService serviceDistrito;
	
	@Autowired
	TipoZonaService serviceTipZon;
	
	@Autowired
	PaisService servicePais;
	
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsdepa = serviceDepartamento.listar();
			if (lsdepa != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsdepa);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarDepartamentos. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), null);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	@PostMapping("/porDepartamento")
	public ResponseWrapper listarPorDepartamento(@RequestBody Departamento departamento) throws Exception {
		
		if (departamento.getIdDepartamento() == null) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/porDepartamento",
					" no se ha especificado un departamento valida", departamento);
		}
		
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsprov = serviceProvincia.listarPorDepartamento(departamento);
			if (lsprov != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsprov);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarProvinciaPorDepa. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/porDepartamento",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), departamento);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	@PostMapping("/porProvincia")
	public ResponseWrapper listarPorProvincia(@RequestBody Provincia provincia) throws Exception {
		
		if (provincia.getIdProvincia() == null) {
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/porProvincia",
				 " no se ha especificado una provincia valida",provincia);
		}
		
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsdist = serviceDistrito.listarPorProvincia(provincia);
			if (lsdist != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsdist);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarDistritos. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/porProvincia",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(), provincia);
		}
	}
	
	@GetMapping("/listarTipoZona")
	public ResponseWrapper listarTipoZona() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsTipZona = serviceTipZon.listar();
			if (lsTipZona != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsTipZona);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarTipoPermiso. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarTipoZona",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}
	
	@GetMapping("/listarPais")
	public ResponseWrapper listarPais() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsPais = servicePais.listar();
			if (lsPais != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsPais);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarPais. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}

}
