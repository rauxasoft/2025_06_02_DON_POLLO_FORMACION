package com.sinensia.hexagonal.modules.dependiente.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.modules.dependiente.domain.model.Dependiente;
import com.sinensia.hexagonal.modules.dependiente.port.in.DependienteInputPort;
import com.sinensia.hexagonal.modules.dependiente.port.out.DependienteOutputPort;

@Service
public class DependienteUseCases implements DependienteInputPort {

	private final DependienteOutputPort dependienteOutputPort;
	
	public DependienteUseCases (DependienteOutputPort dependienteOutputPort){
		this.dependienteOutputPort = dependienteOutputPort;
	}
	
	@Override
	public List<Dependiente> obtenerTodosLosDependientes() {
		return dependienteOutputPort.obtenerTodosLosDependientes();
	}

}
