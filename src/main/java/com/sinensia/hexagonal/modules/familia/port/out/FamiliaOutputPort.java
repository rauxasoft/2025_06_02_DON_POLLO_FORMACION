package com.sinensia.hexagonal.modules.familia.port.out;

import java.util.List;

import com.sinensia.hexagonal.modules.familia.domain.model.Familia;

public interface FamiliaOutputPort {

	List<Familia> obtenerTodasLasFamilias();
	
	Long crearFamilia(Familia familia);
}
