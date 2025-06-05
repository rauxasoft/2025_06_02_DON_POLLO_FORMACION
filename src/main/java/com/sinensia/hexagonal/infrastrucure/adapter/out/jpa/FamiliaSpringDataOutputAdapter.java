package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.FamiliaPL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.FamiliaPLRepository;
import com.sinensia.hexagonal.modules.familia.domain.model.Familia;
import com.sinensia.hexagonal.modules.familia.domain.model.FamiliaId;
import com.sinensia.hexagonal.modules.familia.port.out.FamiliaOutputPort;

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
		
		List<FamiliaPL> familiasPL = familiaPLRepository.findAll();
		
		List<Familia> familias = new ArrayList<>();
		
		for(FamiliaPL familiaPL: familiasPL) {
			FamiliaId familiaId = new FamiliaId(familiaPL.getId());
			Familia familia = new Familia(familiaId, familiaPL.getNombre());
			familias.add(familia);
		}
		
		return familias;
	}

	@Override
	public Long crearFamilia(Familia familia) {
		FamiliaPL familiaPL = mapper.map(familia, FamiliaPL.class);		
		return familiaPLRepository.save(familiaPL).getId();
	}

}
