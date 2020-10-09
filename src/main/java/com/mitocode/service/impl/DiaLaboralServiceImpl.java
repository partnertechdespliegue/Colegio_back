package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Sucursal;
import com.mitocode.model.Turno;
import com.mitocode.repo.DiaLaboralRepo;
import com.mitocode.service.DiaLaboralService;

@Service
public class DiaLaboralServiceImpl implements DiaLaboralService{
	
	@Autowired
	DiaLaboralRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public DiaLaboral registrar(DiaLaboral obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public DiaLaboral modificar(DiaLaboral obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public DiaLaboral encontrar(Integer id) {
		try {
			return repo.findByIdDiaLaboral(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<DiaLaboral> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DiaLaboral> listarPorSucursal(Sucursal sucursal) {
		try {
			return repo.findBySucursalOrderByNumDiaAsc(sucursal);
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
	public Boolean existePorNumDia(Sucursal sucursal, Integer numDia) {
		try {
			return repo.existsBySucursalAndNumDia(sucursal, numDia);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " existePorNumDia. ERROR : " + e.getMessage());
			throw e;
		}
	}

}
