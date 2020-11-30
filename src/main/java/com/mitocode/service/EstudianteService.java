package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Sucursal;

public interface EstudianteService extends ICRUD<Estudiante>{

	List<Estudiante> listarPorColegio(Colegio colegio);

	List<Estudiante> listarPorSucursal(Sucursal sucursal);

	List<Estudiante> listarPorSucursalNivEducaGrago(Sucursal sucursal, NivelEducativo nivelEducativo, Grado grado);

	Boolean existePorNroDoc(String nroDoc);

}
