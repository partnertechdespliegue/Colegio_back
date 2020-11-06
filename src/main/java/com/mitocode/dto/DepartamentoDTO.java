package com.mitocode.dto;

import com.mitocode.model.Colegio;
import com.mitocode.model.DepartamentoColegio;
import com.mitocode.model.PuestoColegio;

public class DepartamentoDTO {

	private DepartamentoColegio departamentoColegio;
	private PuestoColegio puestoColegio;
	private Colegio colegio;

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

	public Colegio getColegio() {
		return colegio;
	}

	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}

}
