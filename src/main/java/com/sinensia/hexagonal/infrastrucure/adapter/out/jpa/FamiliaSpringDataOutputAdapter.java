package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.application.port.out.FamiliaOutputPort;
import com.sinensia.hexagonal.domain.model.Familia;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.FamiliaPL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.FamiliaPLRepository;

@Repository
public class FamiliaSpringDataOutputAdapter implements FamiliaOutputPort {

	private final FamiliaPLRepository familiaPLRepository;
	private final DozerBeanMapper mapper;
	
	public FamiliaSpringDataOutputAdapter(FamiliaPLRepository familiaPLRepository, DozerBeanMapper mapper) {
		this.familiaPLRepository = familiaPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<Familia> obtenerTodasLasFamilias() {
		
		return familiaPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Familia.class))
				.toList();
	}

	@Override
	public Long crearFamilia(Familia familia) {
		FamiliaPL familiaPL = mapper.map(familia, FamiliaPL.class);		
		return familiaPLRepository.save(familiaPL).getId();
	}

}
