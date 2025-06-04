package com.sinensia.hexagonal.application.port.out;

import java.util.List;

import com.sinensia.hexagonal.domain.model.Dependiente;

public interface DependienteOutputPort {

	List<Dependiente> obtenerTodosLosDependientes();

}
