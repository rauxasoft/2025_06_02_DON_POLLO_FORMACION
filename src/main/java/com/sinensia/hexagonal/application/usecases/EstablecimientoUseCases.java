package com.sinensia.hexagonal.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.EstablecimientoInputPort;
import com.sinensia.hexagonal.application.port.out.EstablecimientoOutputPort;
import com.sinensia.hexagonal.domain.exceptions.BusinessException;
import com.sinensia.hexagonal.domain.model.Establecimiento;

@Service
public class EstablecimientoUseCases implements EstablecimientoInputPort {

	private final EstablecimientoOutputPort establecimientoOutputPort;
	
	public EstablecimientoUseCases(EstablecimientoOutputPort establecimientoOutputPort) {
		this.establecimientoOutputPort = establecimientoOutputPort;
	}
	
	@Override
	public Long crearEstablecimiento(Establecimiento establecimiento) {
		
		if(establecimiento.getId() != null) {
			throw new BusinessException("Para crear un establecimiento la id ha de ser null");
		}
		
		return establecimientoOutputPort.crearEstablecimiento(establecimiento);
	}

	@Override
	public Optional<Establecimiento> obtenerEstablecimientoPorId(Long idEstablecimiento) {
		return establecimientoOutputPort.obtenerEstablecimientoPorId(idEstablecimiento);
	}

	@Override
	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		
		Long idEstablecimiento = establecimiento.getId();
		
		if(idEstablecimiento == null) {
			throw new BusinessException("La id de establecimiento no puede ser null");
		}
		
		boolean existe = establecimientoOutputPort.existeEstablecimientoPorId(idEstablecimiento);
		
		if(!existe) {
			throw new BusinessException("No existe el establecimiento con id [" + idEstablecimiento + "]");
		}
		
		establecimientoOutputPort.actualizarEstablecimiento(establecimiento);
		
	}

	@Override
	public List<Establecimiento> obtenerTodosLosEstablecimientos() {
		return establecimientoOutputPort.obtenerTodosLosEstablecimientos();
	}

}
