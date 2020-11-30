package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.Empleado;
import com.mitocode.model.Sucursal;
import com.mitocode.repo.EmpleadoRepo;
import com.mitocode.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	EmpleadoRepo repo;

	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);

	@Override
	public Empleado registrar(Empleado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Empleado modificar(Empleado obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Empleado encontrar(Integer id) {
		try {
			return repo.findByIdEmpleado(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Empleado> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> listarPorColegio(Colegio colegio) {
		try {
			return repo.findByColegio(colegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Empleado> listarPorSucursal(Sucursal sucursal) {
		try {
			return repo.findBySucursal(sucursal);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSucursal. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Empleado> listarPorColegioCodPuesto(Integer idColegio, Integer codPuesto) {
		try {
			return repo.listarPorColegioCodPuesto(idColegio, codPuesto);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegioCodPuesto. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Empleado> listarPorSucursalCodPuesto(Integer idSucursal, Integer codPuesto) {
		try {
			return repo.listarPorSucursalCodPuesto(idSucursal, codPuesto);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSucursalCodPuesto. ERROR : " + e.getMessage());
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
