package com.mitocode.service;

import java.util.List;

import com.mitocode.model.AsistenciaEstudiante;
import com.mitocode.model.HorarioSeccion;

public interface AsistenciaEstudianteService extends ICRUD<AsistenciaEstudiante>{

	List<AsistenciaEstudiante> listarPorSeccion(Integer idSeccion);

	List<AsistenciaEstudiante> listarPorCurso(Integer idCurso);

	List<AsistenciaEstudiante> listarPorHorarioSeccion(HorarioSeccion horarioSeccion);

	List<AsistenciaEstudiante> registrarMasivo(List<AsistenciaEstudiante> lsAsistenciaEstudiante);

}
