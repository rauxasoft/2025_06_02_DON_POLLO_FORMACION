package com.sinensia.hexagonal.modules.familia.domain.model;

import java.util.Objects;

public class FamiliaId {

    private final Long value;

    public FamiliaId(Long value) {
    	
        if (value == null) {
            throw new IllegalArgumentException("El id de familia no puede ser nulo");
        }
        
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamiliaId)) return false;
        FamiliaId that = (FamiliaId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
