package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Apoderado;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Relacion;

public interface RelacionRepo extends JpaRepository<Relacion, Integer>{
	
	List<Relacion> findByEstudiante (Estudiante estudiante);
	
	List<Relacion> findByApoderado (Apoderado apoderado);
	
	boolean existsByEstudianteAndApoderado(Estudiante estudiante, Apoderado apoderado);

}
