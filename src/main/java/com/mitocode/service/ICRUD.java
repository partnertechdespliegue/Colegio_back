package com.mitocode.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T obj);
	T modificar(T obj);
	T encontrar(Integer id);
	List<T> listar();
	Boolean eliminar(Integer id);
}
