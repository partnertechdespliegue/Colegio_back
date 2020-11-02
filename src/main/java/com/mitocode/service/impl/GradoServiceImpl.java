package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.repo.GradoRepo;
import com.mitocode.service.GradoService;

@Service
public class GradoServiceImpl implements GradoService{
	
	@Autowired
	GradoRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Grado registrar(Grado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Grado> registrarList(List<Grado> lsGrado) {
		try {
			return repo.saveAll(lsGrado);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrarList. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Grado modificar(Grado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Grado encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grado> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Grado> listarPorNivelEducativo(NivelEducativo nivelEducativo) {
		try {
			return repo.findByNivelEducativo(nivelEducativo);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorNivelEducativo. ERROR : " + e.getMessage());
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
