package com.sinensia.hexagonal.modules.cliente.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.cliente.domain.model.Cliente;

public interface ClienteOutputPort {

	Optional<Cliente> obtenerClientePorId(Long id);

	List<Cliente> obtenerTodosLosClientes();

	Long crearCliente(Cliente cliente);
	
	boolean existeClientePorNif(String nif);
	
}
