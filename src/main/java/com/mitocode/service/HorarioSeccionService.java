package com.mitocode.service;

import java.sql.Timestamp;
import java.util.List;

import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.Seccion;

public interface HorarioSeccionService extends ICRUD<HorarioSeccion>{

	List<HorarioSeccion> listarPorSeccion(Seccion seccion);

	List<HorarioSeccion> listarporSeccionYDia(Integer idSeccion, Integer idDiaLaboral);

	List<HorarioSeccion> encontrarPorHoraInicioYFin(Integer idSeccion, Integer idDiaLaboral, Timestamp horaInicio,
			Timestamp horaFin);

}
