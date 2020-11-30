package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.AsistenciaEstudiante;
import com.mitocode.model.HorarioSeccion;

public interface AsistenciaEstudianteRepo extends JpaRepository<AsistenciaEstudiante, Integer>{

	AsistenciaEstudiante findByIdAsistenciaEstudiante(Integer id);
	
	public List<AsistenciaEstudiante> findByHorarioSeccion(HorarioSeccion horarioSeccion);

	@Query(value="SELECT ae.* FROM public.asistencia_estudiante ae\r\n"
			+ "INNER JOIN horario_seccion hs ON hs.id_horario_seccion = ae.id_horario_seccion WHERE hs.id_seccion = :idSeccion", nativeQuery = true)
	public List<AsistenciaEstudiante> listarPorSeccion(@Param("idSeccion") Integer idSeccion);
	
	@Query(value="SELECT ae.* FROM public.asistencia_estudiante ae\r\n"
			+ "INNER JOIN horario_seccion hs ON hs.id_horario_seccion = ae.id_horario_seccion WHERE hs.id_curso = :idCurso", nativeQuery = true)
	public List<AsistenciaEstudiante> listarPorCurso(@Param("idCurso") Integer idCurso);
}
