package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Receso;
import com.mitocode.model.Turno;
import com.mitocode.repo.RecesoRepo;
import com.mitocode.service.RecesoService;

@Service
public class RecesoServiceImpl implements RecesoService{

	@Autowired
	RecesoRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Receso registrar(Receso obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Receso modificar(Receso obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Receso encontrar(Integer id) {
		try {
			return repo.findByIdReceso(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Receso> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Receso> listarPorTurno(Turno turno) {
		try {
			return repo.findByTurno(turno);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorTurno. ERROR : " + e.getMessage());
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
