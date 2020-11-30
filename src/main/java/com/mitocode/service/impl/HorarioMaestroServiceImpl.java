package com.mitocode.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.HorarioMaestro;
import com.mitocode.model.HorarioSalon;
import com.mitocode.repo.HorarioMaestroRepo;
import com.mitocode.service.HorarioMaestroService;

@Service
public class HorarioMaestroServiceImpl implements HorarioMaestroService{

	@Autowired
	HorarioMaestroRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public HorarioMaestro registrar(HorarioMaestro obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioMaestro modificar(HorarioMaestro obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public HorarioMaestro encontrar(Integer id) {
		try {
			return repo.findByIdHorarioMaestro(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<HorarioMaestro> listar() {
		// TODO Auto-generated method stub
		return null;
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
	public List<HorarioMaestro> listarporEmpleadoYDia(Integer idEmpleado, Integer idDiaLaboral) {
		try {
			return repo.listarporEmpleadoYDia(idEmpleado, idDiaLaboral);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarporEmpleadoYDia. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<HorarioMaestro> encontrarPorHoraInicioYFin(Integer idEmpleado, Integer idDiaLaboral, Timestamp horaInicio, Timestamp horaFin) {
		try {
			return repo.encontrarPorHoraInicioYFin(idEmpleado, idDiaLaboral, horaInicio, horaFin);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrarPorHoraInicioYFin. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
