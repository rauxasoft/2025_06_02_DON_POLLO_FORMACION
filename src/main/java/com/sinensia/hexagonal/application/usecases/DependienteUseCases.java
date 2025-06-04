package com.sinensia.hexagonal.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.DependienteInputPort;
import com.sinensia.hexagonal.application.port.out.DependienteOutputPort;
import com.sinensia.hexagonal.domain.model.Dependiente;

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
