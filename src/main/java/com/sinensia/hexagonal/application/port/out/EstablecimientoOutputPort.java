package com.sinensia.hexagonal.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.domain.model.Establecimiento;

public interface EstablecimientoOutputPort {

	Long crearEstablecimiento(Establecimiento establecimiento);

	Optional<Establecimiento> obtenerEstablecimientoPorId(Long idEstablecimiento);

	List<Establecimiento> obtenerTodosLosEstablecimientos();

	boolean existeEstablecimientoPorId(Long id);

	void actualizarEstablecimiento(Establecimiento establecimiento);

}
