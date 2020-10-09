package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Colegio;

public interface ColegioRepo extends JpaRepository<Colegio, Integer>{

	Colegio findByIdColegio(Integer idColegio);
}
