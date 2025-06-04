package com.sinensia.hexagonal.domain.model;

public class Dependiente extends Persona {

	private String licenciaManipuladorAlimentos;
	
	public Dependiente() {
		
	}
	
	public String getLicenciaManipuladorAlimentos() {
		return licenciaManipuladorAlimentos;
	}

	public void setLicenciaManipuladorAlimentos(String licenciaManipuladorAlimentos) {
		this.licenciaManipuladorAlimentos = licenciaManipuladorAlimentos;
	}

}
