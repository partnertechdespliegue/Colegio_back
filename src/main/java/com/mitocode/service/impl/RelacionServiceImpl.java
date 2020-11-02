package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Relacion;
import com.mitocode.repo.RelacionRepo;
import com.mitocode.service.RelacionService;

@Service
public class RelacionServiceImpl implements RelacionService{

	@Autowired
	RelacionRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Relacion registrar(Relacion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Relacion modificar(Relacion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Relacion encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Relacion> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Relacion> listarPorEstudiante(Estudiante estudiante) {
		try {
			return repo.findByEstudiante(estudiante);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorEstudiante. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Relacion> listarPorApoderado(Apoderado apoderado) {
		try {
			return repo.findByApoderado(apoderado);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorApoderado. ERROR : " + e.getMessage());
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
	public Boolean existePorEstuYApod(Estudiante estudiante, Apoderado apoderado) {
		try {
			return repo.existsByEstudianteAndApoderado(estudiante, apoderado);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " existePorEstuYApod. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
