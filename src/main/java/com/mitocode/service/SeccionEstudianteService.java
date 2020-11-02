package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Seccion;
import com.mitocode.model.SeccionEstudiante;

public interface SeccionEstudianteService extends ICRUD<SeccionEstudiante>{

	List<SeccionEstudiante> registrarLista(List<SeccionEstudiante> list);
	List<SeccionEstudiante> listarPorSeccion(Seccion seccion);
	List<SeccionEstudiante> listarPorColegio(Integer idColegio);
	List<SeccionEstudiante> listarPorColegioNivEducaGrado(Integer idColegio, Integer idNivEduca, Integer idGrado);

}
