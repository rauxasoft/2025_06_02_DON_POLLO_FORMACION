package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.application.port.out.DependienteOutputPort;
import com.sinensia.hexagonal.domain.model.Dependiente;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.DependientePLRepository;

@Repository
public class DependienteSpringDataOutputAdapter implements DependienteOutputPort {

	private final DependientePLRepository dependientePLRepository;
	private final DozerBeanMapper mapper;
	
	public DependienteSpringDataOutputAdapter(DependientePLRepository dependientePLRepository, DozerBeanMapper mapper) {
		this.dependientePLRepository = dependientePLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<Dependiente> obtenerTodosLosDependientes() {
		
		return dependientePLRepository.findAll().stream()
				.map(x -> mapper.map(x, Dependiente.class))
				.toList();	
	}

}
