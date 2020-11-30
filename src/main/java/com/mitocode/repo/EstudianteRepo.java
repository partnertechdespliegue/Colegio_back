package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.Estudiante;
import com.mitocode.model.Grado;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Sucursal;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

	List<Estudiante> findBySucursal(Sucursal sucursal);
	List<Estudiante> findByColegio(Colegio colegio);
	Estudiante findByIdEstudiante(Integer idEstudiante);
	List<Estudiante> findBySucursalAndNivelEducativoAndGrado(Sucursal sucursal, NivelEducativo nivelEducativo, Grado grado);
	boolean existsByNroDoc (String nroDoc); 

}
