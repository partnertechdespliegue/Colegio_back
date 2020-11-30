package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.AsistenciaEstudiante;
import com.mitocode.model.HorarioSeccion;
import com.mitocode.repo.AsistenciaEstudianteRepo;
import com.mitocode.service.AsistenciaEstudianteService;

@Service
public class AsistenciaEstudianteServiceImpl implements AsistenciaEstudianteService{

	@Autowired
	AsistenciaEstudianteRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public AsistenciaEstudiante registrar(AsistenciaEstudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<AsistenciaEstudiante> registrarMasivo(List<AsistenciaEstudiante> lsAsistenciaEstudiante) {
		try {
			return repo.saveAll(lsAsistenciaEstudiante);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrarMasivo. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public AsistenciaEstudiante modificar(AsistenciaEstudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public AsistenciaEstudiante encontrar(Integer id) {
		try {
			return repo.findByIdAsistenciaEstudiante(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<AsistenciaEstudiante> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AsistenciaEstudiante> listarPorHorarioSeccion(HorarioSeccion horarioSeccion) {
		try {
			return repo.findByHorarioSeccion(horarioSeccion);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorHorarioSeccion. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<AsistenciaEstudiante> listarPorSeccion(Integer idSeccion) {
		try {
			return repo.listarPorSeccion(idSeccion);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSeccion. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<AsistenciaEstudiante> listarPorCurso(Integer idCurso) {
		try {
			return repo.listarPorCurso(idCurso);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorCurso. ERROR : " + e.getMessage());
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

}
