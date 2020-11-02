package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.TipoRelacion;
import com.mitocode.repo.TipoRelacionRepo;
import com.mitocode.service.TipoRelacionService;

@Service
public class TipoRelacionServiceImpl implements TipoRelacionService {

	@Autowired
	TipoRelacionRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public TipoRelacion registrar(TipoRelacion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<TipoRelacion> registrarList(List<TipoRelacion> lsTipoRelacion) {
		try {
			return repo.saveAll(lsTipoRelacion);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrarList. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public TipoRelacion modificar(TipoRelacion obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public TipoRelacion encontrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoRelacion> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoRelacion> listarPorColegio(Colegio colegio) {
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
