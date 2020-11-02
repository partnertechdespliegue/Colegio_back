package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Seccion;
import com.mitocode.model.SeccionEstudiante;

public interface SeccionEstudianteRepo extends JpaRepository<SeccionEstudiante, Integer>{

	List<SeccionEstudiante> findBySeccion(Seccion seccion);
	
	@Query(value="SELECT se.* FROM public.seccion_estudiante se\r\n"
			+ "INNER JOIN estudiante es on es.id_estudiante = se.id_estudiante\r\n"
			+ "INNER JOIN colegio co on co.id_colegio = es.id_colegio WHERE co.id_colegio = :idColegio", nativeQuery = true)
	public List<SeccionEstudiante> listarPorColegio(@Param("idColegio") Integer idColegio);
	
	@Query(value="SELECT se.* FROM public.seccion_estudiante se\r\n"
			+ "INNER JOIN seccion scc on scc.id_seccion = se.id_seccion\r\n"
			+ "INNER JOIN nivel_educativo nve on nve.id_nivel_educativo = scc.id_nivel_educativo\r\n"
			+ "INNER JOIN grado gr on gr.id_grado = scc.id_grado\r\n"
			+ "INNER JOIN estudiante es on es.id_estudiante = se.id_estudiante\r\n"
			+ "INNER JOIN colegio co on co.id_colegio = es.id_colegio \r\n"
			+ "WHERE co.id_colegio = :idColegio and nve.id_nivel_educativo = :idNivEduca and gr.id_grado = :idGrado", nativeQuery = true)
	public List<SeccionEstudiante> listarPorColegioNivEducaGrado(@Param("idColegio") Integer idColegio, @Param("idNivEduca") Integer idNivEduca, @Param("idGrado") Integer idGrado);
}
