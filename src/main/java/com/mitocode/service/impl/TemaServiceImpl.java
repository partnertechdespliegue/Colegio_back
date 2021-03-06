package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Tema;
import com.mitocode.model.TipoCurso;
import com.mitocode.repo.TemaRepo;
import com.mitocode.service.TemaService;

@Service
public class TemaServiceImpl implements TemaService{

	@Autowired
	TemaRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Tema registrar(Tema obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Tema modificar(Tema obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Tema encontrar(Integer id) {
		try {
			return repo.findByIdTema(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Tema> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Tema> listarPorColegio(Integer idColegio) {
		try {
			return repo.listarPorColegio(idColegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Tema> listarPorTipoCurso(TipoCurso tipoCurso) {
		try {
			return repo.findByTipoCurso(tipoCurso);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorTipoCurso. ERROR : " + e.getMessage());
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
