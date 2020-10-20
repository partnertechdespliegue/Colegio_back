package com.mitocode.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Salon;
import com.mitocode.model.Sucursal;
import com.mitocode.repo.HorarioSalonRepo;
import com.mitocode.service.HorarioSalonService;

@Service
public class HorarioSalonServiceImpl implements HorarioSalonService{

	@Autowired
	HorarioSalonRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public HorarioSalon registrar(HorarioSalon obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioSalon modificar(HorarioSalon obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioSalon encontrar(Integer id) {
		try {
			return repo.findByIdHorarioSalon(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<HorarioSalon> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<HorarioSalon> listarPorSalon(Salon salon) {
		try {
			return repo.findBySalon(salon);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSalon. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<HorarioSalon> listarporSalonYDia(Integer idSalon, Integer idDiaLaboral) {
		try {
			return repo.listarporSalonYDia(idSalon, idDiaLaboral);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarporSalonYDia. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean eliminar(Integer id) {
		try {
			if (repo.existsById(id)) {
				repo.deleteById(id);
			}
			return repo.existsById(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " eliminar. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<HorarioSalon> encontrarPorHoraInicioYFin(Integer idDiaLaboral, Timestamp horaInicio, Timestamp horaFin) {
		try {
			return repo.encontrarPorHoraInicioYFin(idDiaLaboral, horaInicio, horaFin);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrarPorHoraInicioYFin. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
