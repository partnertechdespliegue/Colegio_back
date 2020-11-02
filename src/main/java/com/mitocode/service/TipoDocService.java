package com.mitocode.service;

import java.util.List;

import com.mitocode.model.TipoDoc;

public interface TipoDocService extends ICRUD<TipoDoc> {

	List<TipoDoc> listarSinRuc();

}
