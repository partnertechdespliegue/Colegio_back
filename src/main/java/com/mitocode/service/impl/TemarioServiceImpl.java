package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Curso;
import com.mitocode.model.Tema;
import com.mitocode.model.Temario;
import com.mitocode.repo.TemarioRepo;
import com.mitocode.service.TemarioService;

@Service
public class TemarioServiceImpl implements TemarioService{

	@Autowired
	TemarioRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Temario registrar(Temario obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Temario modificar(Temario obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Temario encontrar(Integer id) {
		try {
			return repo.findByIdTemario(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Temario> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Temario> listarPorCurso(Curso curso) {
		try {
			return repo.findByCurso(curso);
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
	
	@Override
	public Boolean existePorCursoYTema(Curso curso, Tema tema) {
		try {
			return repo.existsByCursoAndTema(curso, tema);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " existePorCursoYTema. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
