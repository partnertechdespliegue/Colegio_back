package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.DepartamentoColegio;
import com.mitocode.repo.DepartamentoColegioRepo;
import com.mitocode.service.DepartamentoColegioService;

@Service
public class DepartamentoColegioServiceImpl implements DepartamentoColegioService{

	@Autowired
	DepartamentoColegioRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public DepartamentoColegio registrar(DepartamentoColegio obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public DepartamentoColegio modificar(DepartamentoColegio obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public DepartamentoColegio encontrar(Integer id) {
		try {
			return repo.findByIdDepartamentoColegio(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<DepartamentoColegio> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DepartamentoColegio> listarPorColegio(Colegio colegio) {
		try {
			return repo.findByColegio(colegio);
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
