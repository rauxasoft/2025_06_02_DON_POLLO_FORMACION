package com.sinensia.hexagonal.modules.producto.domain.model;

import java.util.Objects;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class ProductoId {

	private final Long value;

    public ProductoId(Long value) {
        if (value == null || value <= 0) {
            throw new BusinessException("El identificador del producto debe ser un número positivo.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductoId)) return false;
        ProductoId other = (ProductoId) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProductoId{" + "value=" + value + '}';
    }
	
}
