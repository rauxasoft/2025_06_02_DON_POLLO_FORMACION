package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.hexagonal.application.port.in.DependienteInputPort;
import com.sinensia.hexagonal.domain.model.Dependiente;

@RestController
@RequestMapping("/dependientes")
@CrossOrigin
public class DependienteController {
	
	private final DependienteInputPort dependienteInputPort;
	
	public DependienteController(DependienteInputPort dependienteServices) {
		this.dependienteInputPort = dependienteServices;
	}
	
	@GetMapping
	public List<Dependiente> getAll(){
		return dependienteInputPort.obtenerTodosLosDependientes();
	}

}
