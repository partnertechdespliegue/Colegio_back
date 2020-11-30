package com.mitocode.dto;

import com.mitocode.model.Banco;
import com.mitocode.model.Colegio;
import com.mitocode.model.Contrato;
import com.mitocode.model.Departamento;
import com.mitocode.model.DepartamentoColegio;
import com.mitocode.model.DiaLaboral;
import com.mitocode.model.Distrito;
import com.mitocode.model.Empleado;
import com.mitocode.model.Pais;
import com.mitocode.model.Provincia;
import com.mitocode.model.PuestoColegio;
import com.mitocode.model.Sucursal;
import com.mitocode.model.TipoDoc;
import com.mitocode.model.TipoZona;

public class EmpleadoDTO {

	private Empleado empleado;
	private Contrato contrato;
	private Sucursal sucursal;
	private Colegio colegio;
	private TipoDoc tipoDoc;
	private Pais pais;
	private Departamento departamento;
	private Provincia provincia;
	private Distrito distrito;
	private TipoZona tipoZona;
	private DepartamentoColegio departamentoColegio;
	private PuestoColegio puestoColegio;
	private Banco bancoSueldo;
	private DiaLaboral diaLaboral;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

	public TipoDoc getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDoc tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public TipoZona getTipoZona() {
		return tipoZona;
	}

	public void setTipoZona(TipoZona tipoZona) {
		this.tipoZona = tipoZona;
	}

	public DepartamentoColegio getDepartamentoColegio() {
		return departamentoColegio;
	}

	public void setDepartamentoColegio(DepartamentoColegio departamentoColegio) {
		this.departamentoColegio = departamentoColegio;
	}

	public PuestoColegio getPuestoColegio() {
		return puestoColegio;
	}

	public void setPuestoColegio(PuestoColegio puestoColegio) {
		this.puestoColegio = puestoColegio;
	}

	public Banco getBancoSueldo() {
		return bancoSueldo;
	}

	public void setBancoSueldo(Banco bancoSueldo) {
		this.bancoSueldo = bancoSueldo;
	}

	public DiaLaboral getDiaLaboral() {
		return diaLaboral;
	}

	public void setDiaLaboral(DiaLaboral diaLaboral) {
		this.diaLaboral = diaLaboral;
	}

}
