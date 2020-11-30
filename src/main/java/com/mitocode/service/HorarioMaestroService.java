package com.mitocode.service;

import java.sql.Timestamp;
import java.util.List;

import com.mitocode.model.HorarioMaestro;

public interface HorarioMaestroService extends ICRUD<HorarioMaestro>{

	List<HorarioMaestro> listarporEmpleadoYDia(Integer idEmpleado, Integer idDiaLaboral);

	List<HorarioMaestro> encontrarPorHoraInicioYFin(Integer idEmpleado, Integer idDiaLaboral, Timestamp horaInicio,
			Timestamp horaFin);

}
