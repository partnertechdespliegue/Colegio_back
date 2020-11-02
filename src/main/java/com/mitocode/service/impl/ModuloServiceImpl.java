package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Modulo;
import com.mitocode.model.Pagina;
import com.mitocode.model.Perfil;
import com.mitocode.repo.ModuloRepo;
import com.mitocode.repo.PerfilRepo;
import com.mitocode.service.ModuloService;

@Service
public class ModuloServiceImpl implements ModuloService {

	@Autowired
	private ModuloRepo repo;
	
	@Autowired
	private PerfilRepo repo_perfil;
	
	private static final Logger LOG  = LoggerFactory.getLogger(Exception.class);
	
	@Override
	public Modulo registrar(Modulo obj) {
		
		return null;
	}
	

	@Override
	public Modulo modificar(Modulo obj) {
		
		return null;
	}

	@Override
	public Modulo encontrar(Integer id) {
		
		return null;
	}

	@Override
	public List<Modulo> listar() {
	
			return null;
			
	}

	@Override
	public Boolean eliminar(Integer id) {
		
		return null;
	}
	
	@Override
	public List<Modulo> listarModuloPorPerfil(Perfil perfil) {
		try {
			Perfil p = repo_perfil.findByIdPerfil(perfil.getIdPerfil());
			List<Modulo> lsmodulo=repo.listarModuloPorPerfil(perfil.getIdPerfil());
			
			List<Modulo> lsmoduloFinal = new ArrayList<>();
			for (Modulo modulo : lsmodulo) {
				modulo.setLspagina(null);
				List<Pagina> lspaginaFinal = new ArrayList<>();
				for (Pagina pagina : p.getLspaginas()) {
					if (pagina.getModulo().getIdModulo() == modulo.getIdModulo()) {
						lspaginaFinal.add(pagina);
					}
				}
				modulo.setLspagina(lspaginaFinal);
				lsmoduloFinal.add(modulo);
			}
			return lsmoduloFinal;
		}catch(Exception e) {
			LOG.error(this.getClass().getSimpleName() + " listarModuloPorPerfil. ERROR : " + e.getMessage());
			throw e;
		}
	}
}
