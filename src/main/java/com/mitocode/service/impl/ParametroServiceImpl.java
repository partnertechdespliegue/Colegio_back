package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.Parametro;
import com.mitocode.repo.ParametroRepo;
import com.mitocode.service.ParametroService;

@Service
public class ParametroServiceImpl implements ParametroService{

	@Autowired
	ParametroRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Parametro registrar(Parametro obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Parametro modificar(Parametro obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Parametro encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Parametro encontrarPorCodigoYColegio(String codigo, Colegio colegio) {
		try {
			return repo.findByCodigoAndColegio(codigo, colegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrarPorCodigoYColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Parametro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
