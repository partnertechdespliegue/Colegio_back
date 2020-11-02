package com.mitocode.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.HorarioSeccion;
import com.mitocode.model.Seccion;

public interface HorarioSeccionRepo extends JpaRepository<HorarioSeccion, Integer>{

	HorarioSeccion findByIdHorarioSeccion(Integer IdHorarioSeccion);
	List<HorarioSeccion> findBySeccion(Seccion seccion);
	
	@Query(value="SELECT hs.* FROM public.horario_seccion hs\r\n"
			+ "WHERE id_seccion = :idSeccion  and id_dia_laboral = :idDiaLaboral order by hora_inicio asc", nativeQuery = true)
	public List<HorarioSeccion> listarporSalonYDia(@Param("idSeccion") Integer idSeccion, @Param("idDiaLaboral") Integer idDiaLaboral);
	
	@Query(value="SELECT hs.* FROM public.horario_seccion hs\r\n" + 
			"WHERE hs.id_seccion = :idSeccion AND hs.id_dia_laboral = :idDiaLaboral AND (hs.hora_inicio >= :horaInicio OR hs.hora_fin >= :horaInicio) \r\n" + 
			"AND (hs.hora_inicio <= :horaFin OR hs.hora_fin <= :horaFin)", nativeQuery = true)
	public List<HorarioSeccion> encontrarPorHoraInicioYFin(@Param("idSeccion") Integer idSeccion, @Param("idDiaLaboral") Integer idDiaLaboral, 
			@Param("horaInicio") Timestamp horaInicio, @Param("horaFin") Timestamp horaFin);
	
}
