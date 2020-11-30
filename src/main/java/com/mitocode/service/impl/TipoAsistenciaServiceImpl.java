package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.TipoAsistencia;
import com.mitocode.repo.TipoAsistenciaRepo;
import com.mitocode.service.TipoAsistenciaService;

@Service
public class TipoAsistenciaServiceImpl implements TipoAsistenciaService{

	@Autowired
	TipoAsistenciaRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public TipoAsistencia registrar(TipoAsistencia obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoAsistencia modificar(TipoAsistencia obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoAsistencia encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoAsistencia> listar() {
		try {
			return repo.findAll();
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName()+" listar. ERROR : "+e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
