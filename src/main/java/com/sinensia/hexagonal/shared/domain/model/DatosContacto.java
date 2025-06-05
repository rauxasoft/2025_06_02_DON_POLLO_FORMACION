package com.sinensia.hexagonal.shared.domain.model;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public record DatosContacto(String telefono1, String telefono2, String email) {
	
	// Os presento el constructor compacto! Se ejecuta automáticamente después de la asignación de valores
	
	public DatosContacto {
		
		if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
			throw new BusinessException("El email proporcionado no es válido: " + email);
		}
		
	}
	
}
