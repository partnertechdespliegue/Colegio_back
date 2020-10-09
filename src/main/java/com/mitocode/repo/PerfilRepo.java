package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mitocode.model.Perfil;


public interface  PerfilRepo extends JpaRepository<Perfil, Integer>{
	
	Perfil findByIdPerfil(Integer idPerfil);
	
}
