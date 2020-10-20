package com.mitocode.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.HorarioSalon;
import com.mitocode.model.Salon;

public interface HorarioSalonRepo extends JpaRepository<HorarioSalon, Integer>{
	
	HorarioSalon findByIdHorarioSalon(Integer idHorarioSalon);
	List<HorarioSalon> findBySalon(Salon salon);
	
	@Query(value="SELECT hs.* FROM horario_salon hs\r\n" + 
			"WHERE hs.id_dia_laboral = :idDiaLaboral AND (hs.hora_inicio >= :horaInicio OR hs.hora_fin >= :horaInicio) \r\n" + 
			"AND (hs.hora_inicio <= :horaFin OR hs.hora_fin <= :horaFin)", nativeQuery = true)
	public List<HorarioSalon> encontrarPorHoraInicioYFin(@Param("idDiaLaboral") Integer idDiaLaboral, 
			@Param("horaInicio") Timestamp horaInicio, @Param("horaFin") Timestamp horaFin);
	
	@Query(value="SELECT hs.*	FROM horario_salon hs where \r\n" + 
			"id_salon = :idSalon and id_dia_laboral = :idDiaLaboral order by hora_inicio asc", nativeQuery = true)
	public List<HorarioSalon> listarporSalonYDia(@Param("idSalon") Integer idSalon, @Param("idDiaLaboral") Integer idDiaLaboral);

}
