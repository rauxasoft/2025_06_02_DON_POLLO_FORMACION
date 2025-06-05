package com.sinensia.hexagonal.modules.establecimiento.domain.model;

import java.util.Objects;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;
import com.sinensia.hexagonal.shared.domain.model.DatosContacto;
import com.sinensia.hexagonal.shared.domain.model.Direccion;

public class Establecimiento {

    private final EstablecimientoId id;
    private String nombre;
    private Direccion direccion;
    private DatosContacto datosContacto;

    public Establecimiento(EstablecimientoId id, String nombre, Direccion direccion, DatosContacto datosContacto) {
        
    	if (id == null) {
        	throw new BusinessException("El ID del establecimiento no puede ser nulo.");
        }
        
        if (nombre == null || nombre.isBlank()) {
        	throw new BusinessException("El nombre no puede estar vacío.");
        }
        
        if (direccion == null) {
        	throw new BusinessException("La dirección no puede ser nula.");
        }
        
        if (datosContacto == null) {
        	throw new BusinessException("Los datos de contacto no pueden ser nulos.");
        }

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.datosContacto = datosContacto;
    }

    public EstablecimientoId getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public DatosContacto getDatosContacto() {
        return datosContacto;
    }

    public void cambiarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new BusinessException("El nuevo nombre no puede estar vacío.");
        }
        this.nombre = nuevoNombre;
    }

    public void actualizarDireccion(Direccion nuevaDireccion) {
        if (nuevaDireccion == null) {
            throw new BusinessException("La nueva dirección no puede ser nula.");
        }
        this.direccion = nuevaDireccion;
    }

    public void actualizarDatosContacto(DatosContacto nuevosDatosContacto) {
        if (nuevosDatosContacto == null) {
            throw new BusinessException("Los nuevos datos de contacto no pueden ser nulos.");
        }
        this.datosContacto = nuevosDatosContacto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establecimiento)) return false;
        Establecimiento that = (Establecimiento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
