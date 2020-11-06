package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.PuestoColegio;
import com.mitocode.repo.PuestoColegioRepo;
import com.mitocode.service.PuestoColegioService;

@Service
public class PuestoColegioServiceImpl implements PuestoColegioService{

	@Autowired
	PuestoColegioRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public PuestoColegio registrar(PuestoColegio obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public PuestoColegio modificar(PuestoColegio obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public PuestoColegio encontrar(Integer id) {
		try {
			return repo.findByIdPuestoColegio(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<PuestoColegio> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<PuestoColegio> listarPorColegio(Integer idColegio) {
		try {
			return repo.listarPorColegio(idColegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
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
