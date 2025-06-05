package com.sinensia.hexagonal.modules.familia.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.modules.familia.domain.model.Familia;
import com.sinensia.hexagonal.modules.familia.port.in.FamiliaInputPort;
import com.sinensia.hexagonal.modules.familia.port.out.FamiliaOutputPort;

@Service
public class FamiliaUseCases implements FamiliaInputPort {

	private FamiliaOutputPort familiaOutputPort;
	
	public FamiliaUseCases(FamiliaOutputPort familiaOutputPort) {
		this.familiaOutputPort = familiaOutputPort;
	}
	
	@Override
	public List<Familia> obtenerTodasLasFamilias() {
		return familiaOutputPort.obtenerTodasLasFamilias();
	}

	@Override
	public Long crearFamilia(Familia familia) {
		
		// AQUI... 
		
		return familiaOutputPort.crearFamilia(familia);
	}

}
