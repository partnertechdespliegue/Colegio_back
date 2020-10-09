package com.mitocode.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.ResponseWrapper;
import com.mitocode.dto.UsuarioDTO;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;
import com.mitocode.util.Constantes;

@RequestMapping("/api/usuario")
@RestController
public class UsuarioController {

	@Autowired
	IUsuarioService service;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/listar")
	public ResponseWrapper listar() throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsuser = service.listar();
			if (lsuser != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarUsuariosOk);
				response.setAaData(lsuser);
			} else {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarUsuariosOk);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listarUsuarios. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					null);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Usuario usuario = usuarioDTO.getUsuario();
			usuario.setPerfil(usuarioDTO.getPerfil());
			String encrip_pass = passEncoder.encode(usuario.getPassword());
			usuario.setPassword(encrip_pass);
			Usuario buscar_repetido = service.findbyUsername(usuario.getUsername());
			if (buscar_repetido != null) {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarUsuarioRepetido);
			} else {
				Usuario resp_user = service.registrar(usuario);
				if (resp_user != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarUsuarioOk);
					response.setDefaultObj(resp_user);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarUsuarioError);
				}
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrarUsuario. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					usuarioDTO);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/modificar")
	public ResponseWrapper modificar(@Valid @RequestBody Usuario usuario, BindingResult result) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Usuario comparar_user = service.findbyUsername(usuario.getUsername());

			if (!usuario.getPassword().equals(comparar_user.getPassword())) {
				String encrip_pass = passEncoder.encode(usuario.getPassword());
				usuario.setPassword(encrip_pass);
			}

			Usuario resp_user = service.modificar(usuario);
			if (resp_user != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarUsuarioOk);
				response.setDefaultObj(resp_user);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarUsuarioError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " modificarUsuario. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/modificar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					usuario);
		}
	}

}
