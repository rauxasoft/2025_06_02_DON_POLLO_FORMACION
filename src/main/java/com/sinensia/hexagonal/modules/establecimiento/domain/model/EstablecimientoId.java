package com.sinensia.hexagonal.modules.establecimiento.domain.model;

import java.util.Objects;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class EstablecimientoId {

    private final Long value;

    public EstablecimientoId(Long value) {
        if (value == null || value <= 0) {
            throw new BusinessException("El identificador del establecimiento debe ser un número positivo.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EstablecimientoId other)) return false;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "EstablecimientoId{" + "value=" + value + '}';
    }
}
