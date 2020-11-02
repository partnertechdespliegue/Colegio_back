package com.mitocode.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Colegio;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Sucursal;
import com.mitocode.repo.EstudianteRepo;
import com.mitocode.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService{

	@Autowired
	EstudianteRepo repo;
	
	private static final Logger LOG = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public Estudiante registrar(Estudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " registrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Estudiante modificar(Estudiante obj) {
		try {
			return repo.save(obj);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " modificar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public Estudiante encontrar(Integer id) {
		try {
			return repo.findByIdEstudiante(id);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " encontrar. ERROR : " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Estudiante> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Estudiante> listarPorColegio(Colegio colegio) {
		try {
			return repo.findByColegio(colegio);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorColegio. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Estudiante> listarPorSucursal(Sucursal sucursal) {
		try {
			return repo.findBySucursal(sucursal);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSucursal. ERROR : " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public List<Estudiante> listarPorSucursalNivEducaGrago(Sucursal sucursal, NivelEducativo nivelEducativo, Grado grado) {
		try {
			return repo.findBySucursalAndNivelEducativoAndGrado(sucursal, nivelEducativo, grado);
		} catch (Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarPorSucursalNivEducaGrago. ERROR : " + e.getMessage());
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
