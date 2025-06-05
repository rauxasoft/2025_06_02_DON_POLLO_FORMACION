package com.sinensia.hexagonal.modules.familia.domain.model;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class Familia {

    private final FamiliaId id;
    private String nombre;

    public Familia(FamiliaId id, String nombre) {
        
    	if (id == null) {
        	throw new BusinessException("El id de la familia no puede ser nulo");
        }
        
        if (nombre == null || nombre.isBlank()) {
        	throw new BusinessException("El nombre de la familia no puede estar vacío");
        }

        this.id = id;
        this.nombre = nombre;
    }

    public FamiliaId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void actualizarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new BusinessException("El nombre no puede estar vacío");
        }
        this.nombre = nuevoNombre;
    }

    @Override
    public String toString() {
        return "Familia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}