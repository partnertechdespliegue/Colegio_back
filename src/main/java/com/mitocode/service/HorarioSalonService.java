package com.mitocode.service;

import java.sql.Timestamp;
import java.util.List;

import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Salon;

public interface HorarioSalonService extends ICRUD<HorarioSalon>{

	List<HorarioSalon> listarPorSalon(Salon salon);
	List<HorarioSalon> listarporSalonYDia(Integer idSalon, Integer idDiaLaboral);
	List<HorarioSalon> encontrarPorHoraInicioYFin(Integer idSalon, Integer idDiaLaboral, Timestamp horaInicio,
			Timestamp horaFin);

}
