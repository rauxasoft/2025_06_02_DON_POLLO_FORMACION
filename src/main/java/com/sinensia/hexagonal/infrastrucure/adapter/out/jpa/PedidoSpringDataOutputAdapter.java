package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.application.port.out.PedidoOutputPort;
import com.sinensia.hexagonal.domain.model.Pedido;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.PedidoPL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.PedidoPLRepository;

@Repository
public class PedidoSpringDataOutputAdapter implements PedidoOutputPort {

	private final PedidoPLRepository pedidoPLRepository;
	private final DozerBeanMapper mapper;
	
	public PedidoSpringDataOutputAdapter(PedidoPLRepository pedidoPLRepository, DozerBeanMapper mapper) {
		this.pedidoPLRepository = pedidoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<Pedido> obtenerPedidoPorId(Long idPedido) {
		
		return pedidoPLRepository.findById(idPedido).stream()
				.map(x -> mapper.map(x, Pedido.class))
				.findAny();
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidos() {
		
		return pedidoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Pedido.class))
				.toList();
	}

	@Override
	public int obtenerElNumeroTotalDePedidos() {
		return (int) pedidoPLRepository.count();
	}

	@Override
	public boolean existePedidoPorId(Long idPedido) {
		return pedidoPLRepository.existsById(idPedido);
	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);		
		pedidoPLRepository.save(pedidoPL);	
	}

}
