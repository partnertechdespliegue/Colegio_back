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

import com.mitocode.dto.EstudianteDTO;
import com.mitocode.dto.ResponseWrapper;
import com.mitocode.exception.ExceptionResponse;
import com.mitocode.model.Colegio;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Perfil;
import com.mitocode.model.Sucursal;
import com.mitocode.model.Usuario;
import com.mitocode.service.EstudianteService;
import com.mitocode.service.IUsuarioService;
import com.mitocode.util.Constantes;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {
	
	private final String rutaAlmacenamiento = "src/main/resources/estudiante";

	@Autowired
	EstudianteService service;
	
	@Autowired
	IUsuarioService serviceUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/listarPorColegio")
	public ResponseWrapper listarPorColegio(@RequestBody Colegio colegio) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			List lsEstudiante = service.listarPorColegio(colegio);
			if (lsEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarEstudianteOk);
				response.setAaData(lsEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarEstudianteError);
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
			List lsEstudiante = service.listarPorSucursal(sucursal);
			if (lsEstudiante != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgListarEstudianteOk);
				response.setAaData(lsEstudiante);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgListarEstudianteError);
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
			Estudiante resp = service.registrar(armarEstudiante(estudianteDTO));
			if (resp != null) {
				registrarUsuario(resp);
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgRegistrarEstudianteOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgRegistrarEstudianteOk);
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

	private void registrarUsuario(Estudiante estudiante) {
		Usuario usuario = new Usuario();
		usuario.setUsername(estudiante.getNroDoc());
		usuario.setPassword(passEncoder.encode(estudiante.getNroDoc()));
		usuario.setEstado(true);
		usuario.setPerfil(new Perfil(3));
		usuario.setEstudiante(estudiante);
		usuario.setColegio(estudiante.getColegio());
		serviceUsuario.registrar(usuario);
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
			Estudiante resp = service.modificar(armarEstudiante(estudianteDTO));
			if (resp != null) {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgActualizarEstudianteOk);
				response.setDefaultObj(resp);
			} else {
				response.setEstado(Constantes.valTransaccionError);
				response.setMsg(Constantes.msgActualizarEstudianteError);
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

	private Estudiante armarEstudiante(EstudianteDTO dto) {
		Estudiante estudiante = dto.getEstudiante();
		estudiante.setColegio(dto.getColegio());
		estudiante.setSucursal(dto.getSucursal());
		estudiante.setTipoDoc(dto.getTipoDoc());
		estudiante.setNivelEducativo(dto.getNivelEducativo());
		estudiante.setGrado(dto.getGrado());
		estudiante.setPais(dto.getPais());
		estudiante.setDepartamento(dto.getDepartamento());
		estudiante.setProvincia(dto.getProvincia());
		estudiante.setDistrito(dto.getDistrito());
		estudiante.setTipoZona(dto.getTipoZona());
		return estudiante;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DeleteMapping("/{idEstudiante}")
	public ResponseWrapper eliminar(@PathVariable("idEstudiante") Integer idEstudiante) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Estudiante estudiante = service.encontrar(idEstudiante);
			if (estudiante.getFoto() != null) {
				eliminarArchivo(estudiante.getFoto());
			}
			if (!service.eliminar(idEstudiante)) {
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
					idEstudiante);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/asignarFoto")
	public ResponseWrapper urlImagen(@RequestParam("file") MultipartFile file,
			@RequestParam("idEstudiante") Integer idEstudiante) throws Exception {
		try {
			ResponseWrapper response = new ResponseWrapper();
			if (!file.isEmpty()) {
				Estudiante estudiante = service.encontrar(idEstudiante);
				String nombreArchivo = UUID.randomUUID().toString() + "_" + estudiante.getNroDoc() + "."
						+ retornarFormato(file.getOriginalFilename());
				if (estudiante.getFoto() != null) {
					eliminarArchivo(estudiante.getFoto());
				}
				estudiante.setFoto(nombreArchivo);
				Estudiante resp = this.service.modificar(estudiante);
				if (resp != null) {
					Path rutaArchivo = Paths.get(this.rutaAlmacenamiento).resolve(nombreArchivo).toAbsolutePath();
					Files.copy(file.getInputStream(), rutaArchivo);
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgAsignarFotoEstudianteOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgAsignarFotoEstudianteError);
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
	public ResponseWrapper eliminarFoto(@RequestBody Integer idEstudiante) throws Exception {

		try {
			ResponseWrapper response = new ResponseWrapper();
			Estudiante est = service.encontrar(idEstudiante);
			if (est.getFoto() != null) {
				eliminarArchivo(est.getFoto());
				est.setFoto(null);
				
				Estudiante resp = service.modificar(est);
				if (resp != null) {
					response.setEstado(Constantes.valTransaccionOk);
					response.setMsg(Constantes.msgEliminarFotoEstudianteOk);
					response.setDefaultObj(resp);
				} else {
					response.setEstado(Constantes.valTransaccionError);
					response.setMsg(Constantes.msgEliminarFotoEstudianteError);
				}
			} else {
				response.setEstado(Constantes.valTransaccionOk);
				response.setMsg(Constantes.msgEliminarFotoEstudianteOk);
				response.setDefaultObj(est);
			}
			
			return response;
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " eliminarFoto. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/eliminarFoto",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							idEstudiante);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@PostMapping("/existePorNroDoc")
	public boolean existePorNroDoc(@RequestBody String nroDoc) throws Exception {
		try {
			return service.existePorNroDoc(nroDoc);
		} catch (Exception e) {
			System.out.println(this.getClass().getSimpleName() + " existePorNroDoc. ERROR : " + e.getMessage());
			throw new ExceptionResponse(new Date(), this.getClass().getSimpleName() + "/existePorNroDoc",
					e.getStackTrace()[0].getFileName() + " => " + e.getStackTrace()[0].getMethodName() + " => "
							+ e.getClass() + " => message: " + e.getMessage() + "=> linea nro: "
							+ e.getStackTrace()[0].getLineNumber(),
							nroDoc);
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
