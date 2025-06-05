package com.sinensia.hexagonal.modules.producto.domain.model;

import com.sinensia.hexagonal.modules.familia.domain.model.FamiliaId;
import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class Producto {

    private final ProductoId id;
    private String nombre;
    private double precio;
    private boolean disponible;
    private String descripcion;
    private final FamiliaId familiaId;

    public Producto(ProductoId id, String nombre, double precio, boolean disponible, FamiliaId familiaId, String descripcion) {
        
    	if (id == null) {
        	throw new BusinessException("El id del producto no puede ser nulo");
        }
    	
        if (nombre == null || nombre.isBlank()) {
        	throw new BusinessException("El nombre del producto no puede estar vacío");
        }
        
        if (precio < 0) {
        	throw new BusinessException("El precio no puede ser negativo");
        }
        
        if (familiaId == null) {
        	throw new BusinessException("El id de familia no puede ser nulo");
        }

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.familiaId = familiaId;
        this.descripcion = descripcion;
    }

    public ProductoId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public FamiliaId getFamiliaId() {
        return familiaId;
    }
    
    public String getDescripcion() {
		return descripcion;
	}

	public void cambiarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new BusinessException("El nombre no puede estar vacío");
        }
        this.nombre = nuevoNombre;
    }

    public void cambiarPrecio(double nuevoPrecio) {
        if (nuevoPrecio < 0) throw new BusinessException("El precio no puede ser negativo");
        this.precio = nuevoPrecio;
    }
    
    public void cambiarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void activar() {
        this.disponible = true;
    }

    public void desactivar() {
        this.disponible = false;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", descripcion=" + descripcion +
                ", familiaId=" + familiaId +
                '}';
    }
}
