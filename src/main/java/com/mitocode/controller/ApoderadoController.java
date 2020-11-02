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

import com.mitocode.dto.EstudianteDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;
import com.mitocode.service.ApoderadoService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/apoderado")
public class ApoderadoController {
	
	private final String rutaAlmacenamiento = "src/main/resources/apoderado";
	
	@Autowired
	ApoderadoService service;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorColegio")
	public ResponseWrapper listarPorColegio(@RequestBody Colegio colegio) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsApoderado = service.listarPorColegio(colegio);
			if (lsApoderado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarApoderadoOk);
				response.setAaData(lsApoderado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarApoderadoError);
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
			List lsApoderado = service.listarPorSucursal(sucursal);
			if (lsApoderado != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarApoderadoOk);
				response.setAaData(lsApoderado);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarApoderadoError);
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
	@PostMapping("/registrar")
	public ResponseWrapper registrar(@RequestBody EstudianteDTO estudianteDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), estudianteDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Apoderado resp = service.registrar(armarApoderado(estudianteDTO));
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarApoderadoOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/registrar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					estudianteDTO);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PutMapping("/actualizar")
	public ResponseWrapper actualizar(@RequestBody EstudianteDTO estudianteDTO, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					"Error en la validacion: Lista de Errores:" + errors.toString(), estudianteDTO);
		}
		try {
			ResponseWrapper response = new ResponseWrapper();
			Apoderado resp = service.modificar(armarApoderado(estudianteDTO));
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarApoderadoOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarApoderadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " actualizar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/actualizar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
					estudianteDTO);
		}
	}

	private Apoderado armarApoderado(EstudianteDTO dto) {
		Apoderado apoderado = dto.getApoderado();
		apoderado.setColegio(dto.getColegio());
		apoderado.setSucursal(dto.getSucursal());
		apoderado.setTipoDoc(dto.getTipoDoc());
		apoderado.setPais(dto.getPais());
		apoderado.setDepartamento(dto.getDepartamento());
		apoderado.setProvincia(dto.getProvincia());
		apoderado.setDistrito(dto.getDistrito());
		apoderado.setTipoZona(dto.getTipoZona());
		return apoderado;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idApoderado}")
	public ResponseWrapper eliminar(@PathVariable("idApoderado") Integer idApoderado) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Apoderado apoderado = service.encontrar(idApoderado);
			if (apoderado.getFoto() != null) {
				eliminarArchivo(apoderado.getFoto());
			}
			if (!service.eliminar(idApoderado)) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarApoderadoOk);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgEliminarApoderadoError);
			}
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminar",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							idApoderado);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/asignarFoto")
	public ResponseWrapper urlImagen(@RequestParam("file") MultipartFile file,
			@RequestParam("idApoderado") Integer idApoderado) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!file.isEmpty()) {
				Apoderado apoderado = service.encontrar(idApoderado);
				String nombreArchivo = UUID.randomUUID().toString() + "_" + apoderado.getNroDoc() + "."
						+ retornarFormato(file.getOriginalFilename());
				if (apoderado.getFoto() != null) {
					eliminarArchivo(apoderado.getFoto());
				}
				apoderado.setFoto(nombreArchivo);
				Apoderado resp = this.service.modificar(apoderado);
				if (resp != null) {
					Path rutaArchivo = Paths.get(this.rutaAlmacenamiento).resolve(nombreArchivo).toAbsolutePath();
					Files.copy(file.getInputStream(), rutaArchivo);
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgAsignarFotoApoderadoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgAsignarFotoApoderadoError);
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
	public ResponseWrapper eliminarFoto(@RequestBody Integer idApoderado) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Apoderado apoderado = service.encontrar(idApoderado);
			if (apoderado.getFoto() != null) {
				eliminarArchivo(apoderado.getFoto());
				apoderado.setFoto(null);
				
				Apoderado resp = service.modificar(apoderado);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgEliminarFotoApoderadoOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgEliminarFotoApoderadoError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarFotoApoderadoOk);
				response.setDefaultObj(apoderado);
			}
			
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarFoto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarFoto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							idApoderado);
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
