package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.EstablecimientoPL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.EstablecimientoPLRepository;
import com.sinensia.hexagonal.modules.establecimiento.domain.model.Establecimiento;
import com.sinensia.hexagonal.modules.establecimiento.port.out.EstablecimientoOutputPort;

@Repository
public class EstablecimientoSpringDataOutputAdapter implements EstablecimientoOutputPort  {

	private final EstablecimientoPLRepository establecimientoPLRepository;
	private final DozerBeanMapper mapper;
	
	public EstablecimientoSpringDataOutputAdapter(EstablecimientoPLRepository establecimientoPLRepository, DozerBeanMapper mapper) {
		this.establecimientoPLRepository = establecimientoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Long crearEstablecimiento(Establecimiento establecimiento) {
		EstablecimientoPL establecimientoPL = mapper.map(establecimiento, EstablecimientoPL.class);		
		return establecimientoPLRepository.save(establecimientoPL).getId();
	}

	@Override
	public Optional<Establecimiento> obtenerEstablecimientoPorId(Long idEstablecimiento) {
	
		return obtenerEstablecimientoPorId(idEstablecimiento).stream()
				.map(x -> mapper.map(x, Establecimiento.class))
				.findAny();
	}

	@Override
	public List<Establecimiento> obtenerTodosLosEstablecimientos() {

		return establecimientoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Establecimiento.class))
				.toList();
	}

	@Override
	public boolean existeEstablecimientoPorId(Long id) {
		return establecimientoPLRepository.existsById(id);
	}

	@Override
	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		EstablecimientoPL establecimientoPL = mapper.map(establecimiento, EstablecimientoPL.class);		
		establecimientoPLRepository.save(establecimientoPL);	
	}

}
