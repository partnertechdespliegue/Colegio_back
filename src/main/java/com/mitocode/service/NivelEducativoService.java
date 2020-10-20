package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.NivelEducativo;

public interface NivelEducativoService extends ICRUD<NivelEducativo>{

	List<NivelEducativo> registrarList(List<NivelEducativo> lsNivEduc);

	List<NivelEducativo> listarPorColegio(Colegio colegio);

}
