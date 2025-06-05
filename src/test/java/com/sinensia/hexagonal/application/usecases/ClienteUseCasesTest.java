package com.sinensia.hexagonal.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

	@Mock
	private ClienteOutputPort clienteOutputPort;
	
	@InjectMocks
	private ClienteUseCases clienteUseCases;
	
	private Cliente cliente1;
	private Cliente cliente2;
	
	@BeforeEach
	void init() {
		cliente1 = new Cliente();
		cliente2 = new Cliente();
		cliente1.setId(100L);
		cliente2.setId(200L);
	}
	
	@Test
	void se_crea_cliente_ok() {
		
		cliente1.setId(null);
		
		when(clienteOutputPort.crearCliente(cliente1)).thenReturn(34L);
		
		Long idCliente = clienteUseCases.crearCliente(cliente1);
		
		assertEquals(34L, idCliente);
		
	}
	
	@Test
	void lanza_exception_si_cliente_tiene_id() {
		
		assertThrows(BusinessException.class, () -> {
			clienteUseCases.crearCliente(cliente1);
		});
	}
	
	@Test
	void se_obtiene_cliente_por_id() {
		
		when(clienteOutputPort.obtenerClientePorId(100L)).thenReturn(Optional.of(cliente1));
		
		Optional<Cliente> optional = clienteUseCases.obtenerClientePorId(100L);
		
		assertTrue(optional.isPresent());
		assertEquals(100L, optional.get().getId());
	}

	@Test
	void se_obtienen_todos_los_clientes() {
		
		List<Cliente> clientesEsperados = Arrays.asList(cliente1, cliente2);
		
		when(clienteOutputPort.obtenerTodosLosClientes()).thenReturn(clientesEsperados);
		
		List<Cliente> clientes = clienteUseCases.obtenerTodosLosClientes();
		
		assertEquals(2, clientes.size());
		assertTrue(clientes.containsAll(clientesEsperados));
	}

}
