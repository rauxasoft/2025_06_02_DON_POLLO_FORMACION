package com.sinensia.hexagonal.modules.establecimiento.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.establecimiento.domain.model.Establecimiento;

public interface EstablecimientoOutputPort {

	Long crearEstablecimiento(Establecimiento establecimiento);

	Optional<Establecimiento> obtenerEstablecimientoPorId(Long idEstablecimiento);

	List<Establecimiento> obtenerTodosLosEstablecimientos();

	boolean existeEstablecimientoPorId(Long id);

	void actualizarEstablecimiento(Establecimiento establecimiento);

}
