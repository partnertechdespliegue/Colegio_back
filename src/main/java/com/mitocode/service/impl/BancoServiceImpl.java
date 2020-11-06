package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Banco;
import com.mitocode.repo.BancoRepo;
import com.mitocode.service.BancoService;

@Service
public class BancoServiceImpl implements BancoService {

	@Autowired
	BancoRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Banco registrar(Banco obj) {
		return null;
	}

	@Override
	public Banco modificar(Banco obj) {
		return null;
	}

	@Override
	public List<Banco> listar() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarBanco. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Boolean eliminar(Integer id) {
		return null;
	}

	@Override
	public Banco encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
