package com.mitocode.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.dto.EmpleadoDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Colegio;
import com.mitocode.model.Contrato;
import com.mitocode.model.Empleado;
import com.mitocode.model.Perfil;
import com.mitocode.model.PuestoColegio;
import com.mitocode.model.Sucursal;
import com.mitocode.model.Usuario;
import com.mitocode.service.ContratoService;
import com.mitocode.service.EmpleadoService;
import com.mitocode.service.IUsuarioService;
import com.mitocode.service.PuestoColegioService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

	private final String rutaAlmacenamiento = "src/main/resources/empleado";

	@Autowired
	EmpleadoService service;

	@Autowired
	ContratoService serviceContrato;
	
	@Autowired
	PuestoColegioService servicePuesto;
	
	@Autowired
	IUsuarioService serviceUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorColegio")
	public ResponseWrapper listarPorColegio(@RequestBody Colegio colegio) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsEmpleado = service.listarPorColegio(colegio);
			if (lsEmpleado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarEmpleadoOk);
				response.setAaData(lsEmpleado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarEmpleadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorColegio",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					colegio);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorSucursal")
	public ResponseWrapper listarPorSucursal(@RequestBody Sucursal sucursal) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsEmpleado = service.listarPorSucursal(sucursal);
			if (lsEmpleado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarEmpleadoOk);
				response.setAaData(lsEmpleado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarEmpleadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarPorSucursal",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					sucursal);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarMaestroPorColegio")
	public ResponseWrapper listarMaestroPorColegio(@RequestBody Colegio colegio) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsEmpleado = service.listarPorColegioCodPuesto(colegio.getIdColegio(), 1);
			if (lsEmpleado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsEmpleado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarMaestroPorColegio",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					colegio);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarMaestroPorSucursal")
	public ResponseWrapper listarMaestroPorSucursal(@RequestBody Sucursal sucursal) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsEmpleado = service.listarPorSucursalCodPuesto(sucursal.getIdSucursal(), 1);
			if (lsEmpleado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setAaData(lsEmpleado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " listar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/listarMaestroPorSucursal",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					sucursal);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody EmpleadoDTO empleadoDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), empleadoDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Contrato respCon = serviceContrato.registrar(armarContrato(empleadoDTO));
			if (respCon != null) {
				Empleado respEmp = service.registrar(armarEmpleado(empleadoDTO, respCon));
				if (respEmp != null) {
					PuestoColegio puesto = servicePuesto.encontrar(empleadoDTO.getPuestoColegio().getIdPuestoColegio());
					if (puesto.getCodPuesto() == 1) {
						registrarUsuario(respEmp);
					}
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgRegistrarEmpleadoOk);
					response.setDefaultObj(respEmp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgRegistrarEmpleadoError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarEmpleadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					empleadoDTO);
		}
	}

	private void registrarUsuario(Empleado empleado) {
		Usuario usuario = new Usuario();
		usuario.setUsername(empleado.getNroDoc());
		usuario.setPassword(passEncoder.encode(empleado.getNroDoc()));
		usuario.setEstado(true);
		usuario.setPerfil(new Perfil(5));
		usuario.setEmpleado(empleado);
		usuario.setColegio(empleado.getColegio());
		serviceUsuario.registrar(usuario);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody EmpleadoDTO empleadoDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), empleadoDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Contrato respCon = serviceContrato.modificar(armarContrato(empleadoDTO));
			if (respCon != null) {
				Empleado respEmp = service.modificar(armarEmpleado(empleadoDTO, respCon));
				if (respEmp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgActualizarEmpleadoOk);
					response.setDefaultObj(respEmp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgActualizarEmpleadoError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarEmpleadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					empleadoDTO);
		}
	}

	private Contrato armarContrato(EmpleadoDTO dto) {
		Contrato contrato = dto.getContrato();
		contrato.setDepartamentoColegio(dto.getDepartamentoColegio());
		contrato.setPuestoColegio(dto.getPuestoColegio());
		contrato.setBancoSueldo(dto.getBancoSueldo());
		return contrato;
	}

	private Empleado armarEmpleado(EmpleadoDTO dto, Contrato contrato) {
		Empleado empleado = dto.getEmpleado();
		empleado.setColegio(dto.getColegio());
		empleado.setSucursal(dto.getSucursal());
		empleado.setTipoDoc(dto.getTipoDoc());
		empleado.setPais(dto.getPais());
		empleado.setDepartamento(dto.getDepartamento());
		empleado.setProvincia(dto.getProvincia());
		empleado.setDistrito(dto.getDistrito());
		empleado.setTipoZona(dto.getTipoZona());
		empleado.setContrato(contrato);
		return empleado;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idEmpleado}")
	public ResponseWrapper eliminar(@PathVariable("idEmpleado") Integer idEmpleado) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Empleado empleado = service.encontrar(idEmpleado);
			if (empleado.getFoto() != null) {
				eliminarArchivo(empleado.getFoto());
			}
			if (!serviceContrato.eliminar(empleado.getContrato().getIdContrato())) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarEstudianteOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarEstudianteError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idEmpleado);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/asignarFoto")
	public ResponseWrapper urlImagen(@RequestParam("file") MultipartFile file,
			@RequestParam("idEmpleado") Integer idEmpleado) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!file.isEmpty()) {
				Empleado empleado = service.encontrar(idEmpleado);
				String nombreArchivo = UUID.randomUUID().toString() + "_" + empleado.getNroDoc() + "."
						+ retornarFormato(file.getOriginalFilename());
				if (empleado.getFoto() != null) {
					eliminarArchivo(empleado.getFoto());
				}
				empleado.setFoto(nombreArchivo);
				Empleado resp = this.service.modificar(empleado);
				if (resp != null) {
					Path rutaArchivo = Paths.get(this.rutaAlmacenamiento).resolve(nombreArchivo).toAbsolutePath();
					Files.copy(file.getInputStream(), rutaArchivo);
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgAsignarFotoEmpleadoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgAsignarFotoEmpleadoError);
				}
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizarFoto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/cambiar Foto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					file);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@GetMapping("/conseguirFoto/{nombreFoto:.+}")
	public ResponseEntity<Resource> conseguirFoto(@PathVariable String nombreFoto) throws Exception {
		Path rutaArchivo = Paths.get(this.rutaAlmacenamiento).resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
			if (!recurso.exists() && !recurso.isReadable()) {
				throw new RuntimeException("Error no se pudo cargar la imagen " + nombreFoto);
			}
			HttpHeaders cabecera = new HttpHeaders();
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
			return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		} catch (MalformedURLException e) {
			System.out.println(this.getClass().getSimpleName() + " conseguirFoto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/conseguirFoto/" + nombreFoto,
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					nombreFoto);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/eliminarFoto")
	public ResponseWrapper eliminarFoto(@RequestBody Integer idEmpleado) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			Empleado emp = service.encontrar(idEmpleado);
			if (emp.getFoto() != null) {
				eliminarArchivo(emp.getFoto());
				emp.setFoto(null);

				Empleado resp = service.modificar(emp);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgEliminarFotoEmpleadoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgEliminarFotoEmpleadoError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarFotoEmpleadoOk);
				response.setDefaultObj(emp);
			}

			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarFoto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarFoto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					idEmpleado);
		}
	}

	private void eliminarArchivo(String nombreArchivo) {
		Path rutaArchivo = Paths.get(this.rutaAlmacenamiento).resolve(nombreArchivo).toAbsolutePath();
		File archivo = rutaArchivo.toFile();
		if (archivo.exists() && archivo.canRead()) {
			archivo.delete();
		}
	}

	private String retornarFormato(String nombreArchivo) {
		String separador = Pattern.quote(".");
		String[] formato = nombreArchivo.split(separador);
		return formato[formato.length - 1];
	}

}
