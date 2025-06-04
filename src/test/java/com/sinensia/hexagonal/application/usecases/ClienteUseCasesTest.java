package com.sinensia.hexagonal.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.hexagonal.application.port.out.ClienteOutputPort;
import com.sinensia.hexagonal.domain.exceptions.BusinessException;
import com.sinensia.hexagonal.domain.model.Cliente;

@ExtendWith(MockitoExtension.class)
class ClienteUseCasesTest {

	@InjectMocks
	private ClienteUseCases clienteUseCases;
	
	@Mock
	private ClienteOutputPort clienteOutputPort;
	
	@Test
	void se_crea_cliente_ok() {
		
		Cliente cliente = new Cliente();
		cliente.setId(null);
		cliente.setNif("46883221L");
		
		when(clienteOutputPort.crearCliente(cliente)).thenReturn(34L);
		
		Long idCliente = clienteUseCases.crearCliente(cliente);
		
		assertEquals(34L, idCliente);
		
	}
	
	@Test
	void lanza_exception_si_cliente_tiene_id() {
		
		Cliente cliente = new Cliente();
		cliente.setId(100L);
		
		assertThrows(BusinessException.class, () -> {
			clienteUseCases.crearCliente(cliente);
		});
	}
	
	@Test
	void se_obtione_cliente_por_id() {
		
		Cliente cliente = new Cliente();
		cliente.setId(100L);
		
		when(clienteOutputPort.obtenerClientePorId(100L)).thenReturn(Optional.of(cliente));
		
		Optional<Cliente> optional = clienteUseCases.obtenerClientePorId(100L);
		
		assertTrue(optional.isPresent());
		assertEquals(100L, optional.get().getId());
	}

	@Test
	void se_obtienen_todos_los_clientes() {
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		
		cliente1.setId(100L);
		cliente2.setId(200L);
		
		List<Cliente> clientesEsperados = Arrays.asList(cliente1, cliente2);
		
		when(clienteOutputPort.obtenerTodosLosClientes()).thenReturn(clientesEsperados);
		
		List<Cliente> clientes = clienteUseCases.obtenerTodosLosClientes();
		
		assertEquals(2, clientes.size());
		assertTrue(clientes.containsAll(clientesEsperados));
	}

}
