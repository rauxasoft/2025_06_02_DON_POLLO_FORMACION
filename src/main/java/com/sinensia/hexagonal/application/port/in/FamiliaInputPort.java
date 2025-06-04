package com.sinensia.hexagonal.application.port.in;

import java.util.List;

import com.sinensia.hexagonal.domain.model.Familia;

public interface FamiliaInputPort {

	List<Familia> obtenerTodasLasFamilias();
	
	Long crearFamilia(Familia familia);
}
