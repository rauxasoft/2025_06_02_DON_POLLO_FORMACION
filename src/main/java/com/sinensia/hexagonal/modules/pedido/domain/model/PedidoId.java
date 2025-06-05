package com.sinensia.hexagonal.modules.pedido.domain.model;

import java.util.Objects;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class PedidoId {
	
	private final Long value;

    public PedidoId(Long value) {
        if (value == null || value <= 0) {
            throw new BusinessException("El identificador de pedido debe ser un número positivo.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PedidoId)) return false;
        PedidoId other = (PedidoId) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PedidoId{" + "value=" + value + '}';
    }
}
