package com.sinensia.hexagonal.modules.dependiente.port.out;

import java.util.List;

import com.sinensia.hexagonal.modules.dependiente.domain.model.Dependiente;

public interface DependienteOutputPort {

	List<Dependiente> obtenerTodosLosDependientes();

}
