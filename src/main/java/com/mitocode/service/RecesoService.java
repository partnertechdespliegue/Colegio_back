package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Receso;
import com.mitocode.model.Turno;

public interface RecesoService extends ICRUD<Receso>{

	List<Receso> listarPorTurno(Turno turno);

}
