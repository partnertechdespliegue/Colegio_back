package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Relacion;

public interface RelacionService extends ICRUD<Relacion>{

	List<Relacion> listarPorEstudiante(Estudiante estudiante);

	List<Relacion> listarPorApoderado(Apoderado apoderado);

	Boolean existePorEstuYApod(Estudiante estudiante, Apoderado apoderado);

}
