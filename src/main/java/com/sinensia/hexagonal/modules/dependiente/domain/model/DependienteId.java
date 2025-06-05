package com.sinensia.hexagonal.modules.dependiente.domain.model;

import java.util.Objects;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class DependienteId {

    private final Long value;

    public DependienteId(Long value) {
    	
        if (value == null || value <= 0) {
            throw new BusinessException("El identificador del dependiente debe ser un número positivo.");
        }
        
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DependienteId other)) return false;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "DependienteId{" + "value=" + value + '}';
    }
   
}