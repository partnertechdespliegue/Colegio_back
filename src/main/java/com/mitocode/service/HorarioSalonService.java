package com.mitocode.service;

import java.sql.Timestamp;
import java.util.List;

import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Salon;

public interface HorarioSalonService extends ICRUD<HorarioSalon>{

	List<HorarioSalon> listarPorSalon(Salon salon);
	List<HorarioSalon> encontrarPorHoraInicioYFin(Integer idDiaLaboral, Timestamp horaInicio, Timestamp horaFin);
	List<HorarioSalon> listarporSalonYDia(Integer idSalon, Integer idDiaLaboral);

}
