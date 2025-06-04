package com.sinensia.hexagonal.application.port.out;

import java.util.List;

import com.sinensia.hexagonal.domain.model.Familia;

// Este interface también se le suele llamar FamiliaRepository

public interface FamiliaOutputPort {

	List<Familia> obtenerTodasLasFamilias();
	
	Long crearFamilia(Familia familia);
}
