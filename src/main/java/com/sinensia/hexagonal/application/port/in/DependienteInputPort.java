package com.sinensia.hexagonal.application.port.in;

import java.util.List;

import com.sinensia.hexagonal.domain.model.Dependiente;

public interface DependienteInputPort {

	List<Dependiente> obtenerTodosLosDependientes();
}
