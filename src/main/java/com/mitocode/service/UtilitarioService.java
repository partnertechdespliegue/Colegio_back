package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Grado;
import com.mitocode.model.Modulo;
import com.mitocode.model.NivelEducativo;
import com.mitocode.model.Pagina;
import com.mitocode.model.Perfil;
import com.mitocode.model.Usuario;

public interface UtilitarioService {
	
	public String insertarDatosModulo(List<Modulo> modulos);
	public String insertarDatosPagina(List<Pagina> paginas);
	public String insertarDatosPerfil(List<Perfil> perfiles);
	public String insertarDatosUsuarios(List<Usuario> usuarios);
	public String insertarDatosNivelEducativo(List<NivelEducativo> nivelesEducativo);
	public String insertarDatosGrado(List<Grado> grados);
	public String insertarDatosDepartamento();
	public String insertarDatosProvincia();
	public String insertarDatosDistrito();
}
