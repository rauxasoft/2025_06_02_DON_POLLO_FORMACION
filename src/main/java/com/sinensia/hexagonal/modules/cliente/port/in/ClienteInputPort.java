package com.sinensia.hexagonal.modules.cliente.port.in;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.cliente.domain.model.Cliente;

public interface ClienteInputPort {
	
	Long crearCliente(Cliente cliente);
	
	Optional<Cliente> obtenerClientePorId(Long id);
	
	List<Cliente> obtenerTodosLosClientes();

}
