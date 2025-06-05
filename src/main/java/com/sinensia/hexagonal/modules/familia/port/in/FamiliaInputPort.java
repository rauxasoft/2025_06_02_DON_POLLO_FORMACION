package com.sinensia.hexagonal.modules.familia.port.in;

import java.util.List;

import com.sinensia.hexagonal.modules.familia.domain.model.Familia;

public interface FamiliaInputPort {

	List<Familia> obtenerTodasLasFamilias();
	
	Long crearFamilia(Familia familia);
}
