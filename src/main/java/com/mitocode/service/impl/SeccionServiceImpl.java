package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Seccion;
import com.mitocode.model.Sucursal;
import com.mitocode.repo.SeccionRepo;
import com.mitocode.service.SeccionService;

@Service
public class SeccionServiceImpl implements SeccionService{

	@Autowired
	SeccionRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public Seccion registrar(Seccion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Seccion modificar(Seccion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Seccion encontrar(Integer id) {
		try {
			return repo.findByIdSeccion(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Seccion> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Seccion> listarPorSucursal(Sucursal sucursal) {
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

}
