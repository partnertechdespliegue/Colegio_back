package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.TipoDoc;

public interface TipoDocRepo extends JpaRepository<TipoDoc, Integer>{

	@Query(value = "SELECT id_tipo_doc, descripcion\r\n" + 
			"	FROM tipo_doc where id_tipo_doc != :idRuc", nativeQuery = true)
	List<TipoDoc> listarSinRuc(@Param("idRuc") Integer idRuc);
	
}
