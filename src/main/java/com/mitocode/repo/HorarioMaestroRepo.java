package com.mitocode.repo;

import com.mitocode.model.HorarioMaestro;
import com.mitocode.model.Salon;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioMaestroRepo extends JpaRepository<HorarioMaestro, Integer>{

	HorarioMaestro findByIdHorarioMaestro (Integer id);
	List<HorarioMaestro> findBySalon(Salon salon);
	
	@Query(value="SELECT hm.* FROM public.horario_maestro hm\r\n"
			+ "WHERE  hm.id_empleado = :idEmpleado AND hm.id_dia_laboral = :idDiaLaboral \r\n"
			+ "AND (hm.hora_inicio >= :horaInicio OR hm.hora_fin >= :horaInicio) \r\n"
			+ "AND (hm.hora_inicio <= :horaFin OR hm.hora_fin <= :horaFin)", nativeQuery = true)
	public List<HorarioMaestro> encontrarPorHoraInicioYFin(@Param("idEmpleado") Integer idEmpleado, @Param("idDiaLaboral") Integer idDiaLaboral, 
			@Param("horaInicio") Timestamp horaInicio, @Param("horaFin") Timestamp horaFin);

	@Query(value="SELECT hm.* FROM public.horario_maestro hm\r\n"
			+ "WHERE id_empleado = :idEmpleado and id_dia_laboral = :idDiaLaboral order by hora_inicio asc", nativeQuery = true)
	public List<HorarioMaestro> listarporEmpleadoYDia(@Param("idEmpleado") Integer idEmpleado, @Param("idDiaLaboral") Integer idDiaLaboral);
	
}
