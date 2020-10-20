package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;
import com.mitocode.model.NivelEducativo;

public interface NivelEducativoRepo extends JpaRepository<NivelEducativo, Integer>{

	List<NivelEducativo> findByColegio(Colegio colegio);
}
