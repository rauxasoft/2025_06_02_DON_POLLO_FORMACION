package com.sinensia.hexagonal.shared.domain.model;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public record DatosPersonales(
	    String nombre,
	    String apellidos,
	    String dni,
	    Direccion direccion,
	    DatosContacto datosContacto
	) {
	
	    public DatosPersonales {
	    	
	        if (nombre == null || nombre.isBlank()) {
	            throw new BusinessException("El nombre no puede estar vacío.");
	        }
	        
	        if (apellidos == null || apellidos.isBlank()) {
	            throw new BusinessException("Los apellidos no pueden estar vacíos.");
	        }
	        
	        if (dni == null || dni.isBlank()) {
	            throw new BusinessException("El DNI no puede estar vacío.");
	        }
	        
	        if (direccion == null) {
	            throw new BusinessException("La dirección no puede ser nula.");
	        }
	        
	        if (datosContacto == null) {
	            throw new BusinessException("Los datos de contacto no pueden ser nulos.");
	        }
	    }
	}

/*

	Este diseño:

		- Asegura inmutabilidad.
		- Aplica validación desde el dominio.
		- Es fácilmente integrable en cualquier entidad como Cliente o Dependiente.

*/