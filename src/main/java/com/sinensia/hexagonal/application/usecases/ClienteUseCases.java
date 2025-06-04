package com.sinensia.hexagonal.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.ClienteInputPort;
import com.sinensia.hexagonal.application.port.out.ClienteOutputPort;
import com.sinensia.hexagonal.domain.exceptions.BusinessException;
import com.sinensia.hexagonal.domain.model.Cliente;

@Service
public class ClienteUseCases implements ClienteInputPort {

	private final ClienteOutputPort clienteOutputPort;
	
	public ClienteUseCases(ClienteOutputPort clienteOutputPort) {
		this.clienteOutputPort = clienteOutputPort;
	}
	
	@Override
	public Long crearCliente(Cliente cliente) {
		
		if(cliente.getId() != null) {
			throw new BusinessException("Para crear un cliente la id ha de ser null");
		}
		
		boolean existe = clienteOutputPort.existeClientePorNif(cliente.getNif());
		
		if(existe) {
			throw new BusinessException("Ya existe un cliente con nif [" + cliente.getNif() + "]");
		}
		
		return clienteOutputPort.crearCliente(cliente);
	}

	@Override
	public Optional<Cliente> obtenerClientePorId(Long idCliente) {
		return clienteOutputPort.obtenerClientePorId(idCliente);
	}

	@Override
	public List<Cliente> obtenerTodosLosClientes() {
		return clienteOutputPort.obtenerTodosLosClientes();
	}

}
