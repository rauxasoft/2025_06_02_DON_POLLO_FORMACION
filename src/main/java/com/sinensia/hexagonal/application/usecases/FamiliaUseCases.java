package com.sinensia.hexagonal.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.FamiliaInputPort;
import com.sinensia.hexagonal.application.port.out.FamiliaOutputPort;
import com.sinensia.hexagonal.domain.model.Familia;

@Service
public class FamiliaUseCases implements FamiliaInputPort{

	private FamiliaOutputPort familiaOutputPort;
	
	public FamiliaUseCases(FamiliaOutputPort familiaOutputPort) {
		this.familiaOutputPort = familiaOutputPort;
	}
	
	@Override
	public List<Familia> obtenerTodasLasFamilias(){
		return familiaOutputPort.obtenerTodasLasFamilias();
	}
	
	@Override
	public Long crearFamilia(Familia familia) {
		return familiaOutputPort.crearFamilia(familia);
	}

}
