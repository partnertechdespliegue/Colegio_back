package com.mitocode.service;

import java.util.List;

import com.mitocode.model.PuestoColegio;

public interface PuestoColegioService extends ICRUD<PuestoColegio>{

	List<PuestoColegio> listarPorColegio(Integer idColegio);

}
