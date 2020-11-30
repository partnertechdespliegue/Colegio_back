package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Colegio;
import com.mitocode.model.Sucursal;
import com.mitocode.repo.ApoderadoRepo;
import com.mitocode.service.ApoderadoService;

@Service
public class ApoderadoServiceImpl implements ApoderadoService{
	
	@Autowired
	ApoderadoRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Apoderado registrar(Apoderado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Apoderado modificar(Apoderado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Apoderado encontrar(Integer id) {
		try {
			return repo.findByIdApoderado(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Apoderado> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Apoderado> listarPorColegio(Colegio colegio) {
		try {
			return repo.findByColegio(colegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Apoderado> listarPorSucursal(Sucursal sucursal) {
		try {
			return repo.findBySucursal(sucursal);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSucursal. ERROR : " + e.getMessage());
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
	public Boolean existePorNroDoc(String nroDoc) {
		try {
			return repo.existsByNroDoc(nroDoc);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " existePorNroDoc. ERROR : " + e.getMessage());
			throw e;
		}
	}
}
