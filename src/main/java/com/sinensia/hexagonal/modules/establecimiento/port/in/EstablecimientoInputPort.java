package com.sinensia.hexagonal.modules.establecimiento.port.in;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.establecimiento.domain.model.Establecimiento;

public interface EstablecimientoInputPort {

	/**
	 * Si la id no es null lanza BusinessException
	 * 
	 */
	Long crearEstablecimiento(Establecimiento establecimiento);
	
	Optional<Establecimiento> obtenerEstablecimientoPorId(Long idEstablecimiento);
	
	/**
	 * Si la id es null o no existe lanza BusinessException
	 * 
	 */
	void actualizarEstablecimiento(Establecimiento establecimiento);
	
	List<Establecimiento> obtenerTodosLosEstablecimientos();
			
}
