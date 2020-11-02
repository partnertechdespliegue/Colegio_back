package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Seccion;
import com.mitocode.model.SeccionEstudiante;
import com.mitocode.repo.SeccionEstudianteRepo;
import com.mitocode.service.SeccionEstudianteService;

@Service
public class SeccionEstudianteServiceImpl implements SeccionEstudianteService{
	
	@Autowired
	SeccionEstudianteRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public SeccionEstudiante registrar(SeccionEstudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<SeccionEstudiante> registrarLista(List<SeccionEstudiante> list) {
		try {
			return repo.saveAll(list);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public SeccionEstudiante modificar(SeccionEstudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public SeccionEstudiante encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeccionEstudiante> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<SeccionEstudiante> listarPorColegio(Integer idColegio) {
		try {
			return repo.listarPorColegio(idColegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<SeccionEstudiante> listarPorColegioNivEducaGrado(Integer idColegio, Integer idNivEduca, Integer idGrado) {
		try {
			return repo.listarPorColegioNivEducaGrado(idColegio, idNivEduca, idGrado);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegioNivEducaGrado. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<SeccionEstudiante> listarPorSeccion(Seccion seccion) {
		try {
			return repo.findBySeccion(seccion);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSeccion. ERROR : " + e.getMessage());
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
