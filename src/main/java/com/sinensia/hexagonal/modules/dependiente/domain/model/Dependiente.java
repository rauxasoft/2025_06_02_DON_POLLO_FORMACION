package com.sinensia.hexagonal.modules.dependiente.domain.model;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;
import com.sinensia.hexagonal.shared.domain.model.DatosContacto;
import com.sinensia.hexagonal.shared.domain.model.DatosPersonales;

public class Dependiente {

    private final DependienteId id;
    private DatosPersonales datosPersonales;
    private String manipuladorAlimentos; // formato tipo "RS-0090223"

    public Dependiente(DependienteId id, DatosPersonales datosPersonales, DatosContacto datosContacto, String manipuladorAlimentos) {
        
    	if (id == null) {
        	throw new BusinessException("El id del dependiente no puede ser nulo");
        }
        
        if (datosPersonales == null) {
        	throw new BusinessException("Los datos personales no pueden ser nulos");
        }
        
        if (datosContacto == null) {
        	throw new BusinessException("Los datos de contacto no pueden ser nulos");
        }
        
        if (manipuladorAlimentos == null || manipuladorAlimentos.isBlank()) {
            throw new BusinessException("El carnet de manipulador de alimentos no puede ser nulo ni vacío");
        }

        this.id = id;
        this.datosPersonales = datosPersonales;
        this.manipuladorAlimentos = manipuladorAlimentos;
    }

    public DependienteId getId() {
        return id;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public String getManipuladorAlimentos() {
        return manipuladorAlimentos;
    }

    public void actualizarDatos(DatosPersonales nuevosDatos, DatosContacto nuevoContacto, String nuevoCarnet) {
        
    	if (nuevosDatos == null) {
        	throw new BusinessException("Los datos personales no pueden ser nulos");
        }
    	
        if (nuevoContacto == null) {
        	throw new BusinessException("Los datos de contacto no pueden ser nulos");
        }
        
        if (nuevoCarnet == null || nuevoCarnet.isBlank()) {
            throw new BusinessException("El carnet de manipulador de alimentos no puede ser nulo ni vacío");
        }

        this.datosPersonales = nuevosDatos;
        this.manipuladorAlimentos = nuevoCarnet;
    }

    @Override
    public String toString() {
        return "Dependiente{" +
                "id=" + id +
                ", datosPersonales=" + datosPersonales +
                ", manipuladorAlimentos='" + manipuladorAlimentos + '\'' +
                '}';
    }
}

/*
 
¿Por qué crear una clase (Value Object) en lugar de usar String?
 
Opción	    				Pros																		Contras
String						✅ Menos código																⚠️ Se repite lógica
							✅ Más simple																⚠️ No encapsula el formato
																										⚠️ Difícil de validar en todos los puntos
																	
Value Object				✅ Valida el formato desde el constructor									⚠️ Un poco más de código inicial
							✅ El dominio “habla” mejor (CarnetManipuladorAlimentos es más expresivo)
							✅ Puede extenderse fácilmente (fecha de expiración, por ejemplo)
							
							
							
							
📌 Recomendación

En un modelo DDD ortodoxo y expresivo como el que estás construyendo, sí es recomendable encapsularlo como CarnetManipuladorAlimentos, especialmente porque:

Tiene una estructura específica y validable.

- Su significado no es genérico, es parte del lenguaje del dominio.
- Podría ampliarse más adelante (por ejemplo: fecha de obtención, entidad emisora...).



 */
 
