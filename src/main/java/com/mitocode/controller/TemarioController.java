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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitocode.dto.CursoDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Curso;
import com.mitocode.model.Tema;
import com.mitocode.model.Temario;
import com.mitocode.service.TemarioService;
import com.mitocode.util.Constantes;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temario")
public class TemarioController {

	@Autowired
	TemarioService service;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listar")
	public ResponseWrapper listar(@RequestBody Curso curso) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsTemario = service.listarPorCurso(curso);
			if (lsTemario != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarTemarioOk);
				response.setAaData(lsTemario);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarTemarioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					curso);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody CursoDTO cursoDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), cursoDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();

			Curso curso = cursoDTO.getCurso();
			Tema tema = cursoDTO.getTema();

			if (!service.existePorCursoYTema(curso, tema)) {
				Temario temario = new Temario();
				temario.setCurso(curso);
				temario.setTema(tema);
				Temario resp = service.registrar(temario);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarTemarioOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarTemarioError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionYaExiste);
				response.setMsg(Constantes.msgExisteTemario);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					cursoDTO);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idTemario}")
	public ResponseWrapper eliminar(@PathVariable("idTemario") Integer idTemario) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!service.eliminar(idTemario)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarTemarioOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarTemarioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idTemario);
		}
	}

}
