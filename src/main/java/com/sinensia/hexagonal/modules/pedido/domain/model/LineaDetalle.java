package com.sinensia.hexagonal.modules.pedido.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.sinensia.hexagonal.modules.producto.domain.model.ProductoId;
import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class LineaDetalle {

    private final ProductoId productoId;
    private final int cantidad;
    private final BigDecimal precioUnitario;

    public LineaDetalle(ProductoId productoId, int cantidad, BigDecimal precioUnitario) {
        
    	if (productoId == null) {
            throw new BusinessException("La línea de detalle debe tener un producto asociado.");
        }
        
    	if (cantidad <= 0) {
            throw new BusinessException("La cantidad debe ser mayor que cero.");
        }
        
    	if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El precio unitario debe ser mayor que cero.");
        }

        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal calcularImporte() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaDetalle)) return false;
        LineaDetalle that = (LineaDetalle) o;
        return productoId.equals(that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId);
    }
}
