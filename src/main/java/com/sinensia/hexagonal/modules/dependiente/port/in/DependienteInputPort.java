package com.sinensia.hexagonal.modules.dependiente.port.in;

import java.util.List;

import com.sinensia.hexagonal.modules.dependiente.domain.model.Dependiente;

public interface DependienteInputPort {

	List<Dependiente> obtenerTodosLosDependientes();
}
