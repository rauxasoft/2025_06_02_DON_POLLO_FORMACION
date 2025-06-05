package com.sinensia.hexagonal.modules.cliente.domain.model;

import com.sinensia.hexagonal.shared.domain.exception.BusinessException;
import com.sinensia.hexagonal.shared.domain.model.DatosContacto;
import com.sinensia.hexagonal.shared.domain.model.DatosPersonales;

public class Cliente {

    private final ClienteId id;
    private DatosPersonales datosPersonales;
    private boolean gold;
    private final String nif;

    public Cliente(ClienteId id, DatosPersonales datosPersonales, DatosContacto datosContacto, boolean gold, String nif) {
        
    	if (id == null) {
    		throw new BusinessException("El id del cliente no puede ser nulo");
    	}
    	
        if (datosPersonales == null) {
        	throw new BusinessException("Los datos personales no pueden ser nulos");
        }
        
        // Aqui validar el NIF
        if (nif == null) {
        	throw new BusinessException("El NIF no es válido");
        }
       
        this.id = id;
        this.datosPersonales = datosPersonales; 
        this.gold = gold;
        this.nif = nif;
    }

    public ClienteId getId() {
        return id;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }
    
    public String getNif() {
    	return nif;
    }

    public boolean isGold() {
        return gold;
    }

    public void actualizarDatos(DatosPersonales nuevosDatos, boolean esGold) {
        
    	if (nuevosDatos == null) {
        	throw new BusinessException("Los datos personales no pueden ser nulos");
        }
      
        this.datosPersonales = nuevosDatos;
        
        this.gold = esGold;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", datosPersonales=" + datosPersonales +
                ", gold=" + gold +
                '}';
    }
}
