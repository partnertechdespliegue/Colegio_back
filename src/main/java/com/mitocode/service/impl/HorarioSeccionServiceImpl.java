package com.mitocode.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.Seccion;
import com.mitocode.repo.HorarioSeccionRepo;
import com.mitocode.service.HorarioSeccionService;

@Service
public class HorarioSeccionServiceImpl implements HorarioSeccionService{

	@Autowired
	HorarioSeccionRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public HorarioSeccion registrar(HorarioSeccion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioSeccion modificar(HorarioSeccion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioSeccion encontrar(Integer id) {
		try {
			return repo.findByIdHorarioSeccion(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<HorarioSeccion> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<HorarioSeccion> listarPorSeccion(Seccion seccion) {
		try {
			return repo.findBySeccion(seccion);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSeccion. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<HorarioSeccion> listarporSeccionYDia(Integer idSeccion, Integer idDiaLaboral) {
		try {
			return repo.listarporSalonYDia(idSeccion, idDiaLaboral);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarporSeccionYDia. ERROR : " + e.getMessage());
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
	public List<HorarioSeccion> encontrarPorHoraInicioYFin(Integer idSeccion, Integer idDiaLaboral, Timestamp horaInicio, Timestamp horaFin) {
		try {
			return repo.encontrarPorHoraInicioYFin(idSeccion, idDiaLaboral, horaInicio, horaFin);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrarPorHoraInicioYFin. ERROR : " + e.getMessage());
			throw e;
		}
	}
}
