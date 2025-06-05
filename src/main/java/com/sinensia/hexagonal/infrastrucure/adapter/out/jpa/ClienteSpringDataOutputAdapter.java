package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.ClientePL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.ClientePLRepository;
import com.sinensia.hexagonal.modules.cliente.domain.model.Cliente;
import com.sinensia.hexagonal.modules.cliente.port.out.ClienteOutputPort;

@Repository
public class ClienteSpringDataOutputAdapter implements ClienteOutputPort{

	private final ClientePLRepository clientePLRepository;
	private final DozerBeanMapper mapper;
	
	public ClienteSpringDataOutputAdapter(ClientePLRepository clientePLRepository, DozerBeanMapper mapper) {
		this.clientePLRepository = clientePLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<Cliente> obtenerClientePorId(Long idCliente) {
		
		return clientePLRepository.findById(idCliente).stream()
				.map(x -> mapper.map(x, Cliente.class))
				.findAny();
	}

	@Override
	public List<Cliente> obtenerTodosLosClientes() {
		
		return clientePLRepository.findAll().stream()
				.map(x -> mapper.map(x, Cliente.class))
				.toList();
	}

	@Override
	public Long crearCliente(Cliente cliente) {
		
		// A este create le importa un bledo la lógica de negocio.
		// Cumple órdenes
		
		ClientePL clientePL = mapper.map(cliente, ClientePL.class);		
		return clientePLRepository.save(clientePL).getId();
	}

	@Override
	public boolean existeClientePorNif(String nif) {
		return clientePLRepository.existsByNif(nif);
	}

}
