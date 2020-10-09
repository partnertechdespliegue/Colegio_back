package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;

public interface GradoService extends ICRUD<Grado>{

	List<Grado> listarPorNivelEducativo(NivelEducativo nivelEducativo);

}
