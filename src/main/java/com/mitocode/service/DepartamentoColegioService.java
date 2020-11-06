package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Colegio;
import com.mitocode.model.DepartamentoColegio;

public interface DepartamentoColegioService extends ICRUD<DepartamentoColegio>{

	List<DepartamentoColegio> listarPorColegio(Colegio colegio);

}
