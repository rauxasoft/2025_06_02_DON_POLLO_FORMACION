package com.sinensia.hexagonal.application.port.in;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.domain.model.Cliente;

public interface ClienteInputPort {
	
	Long crearCliente(Cliente cliente);
	
	Optional<Cliente> obtenerClientePorId(Long id);
	
	List<Cliente> obtenerTodosLosClientes();

}
