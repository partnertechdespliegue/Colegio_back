package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Modulo;
import com.mitocode.model.Perfil;

public interface ModuloService extends ICRUD<Modulo> {

	List<Modulo> listarModuloPorPerfil(Perfil perfil);

}
