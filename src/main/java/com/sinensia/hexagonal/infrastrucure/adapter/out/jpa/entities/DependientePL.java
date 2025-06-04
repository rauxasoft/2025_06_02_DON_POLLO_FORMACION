package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="EMPLEADOS")
public class DependientePL extends PersonaPL {

	private String licenciaManipuladorAlimentos;
	
	public DependientePL() {
		
	}
	
	public String getLicenciaManipuladorAlimentos() {
		return licenciaManipuladorAlimentos;
	}

	public void setLicenciaManipuladorAlimentos(String licenciaManipuladorAlimentos) {
		this.licenciaManipuladorAlimentos = licenciaManipuladorAlimentos;
	}

}
