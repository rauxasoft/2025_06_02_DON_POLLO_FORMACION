package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.PedidoPL;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.PedidoPLRepository;
import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;
import com.sinensia.hexagonal.modules.pedido.domain.model.PedidoId;
import com.sinensia.hexagonal.modules.pedido.port.out.PedidoOutputPort;

@Repository
public class PedidoSpringDataOutputAdapter implements PedidoOutputPort {

	private final PedidoPLRepository pedidoPLRepository;
	private final DozerBeanMapper mapper;
	
	public PedidoSpringDataOutputAdapter(PedidoPLRepository pedidoPLRepository, DozerBeanMapper mapper) {
		this.pedidoPLRepository = pedidoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<Pedido> obtenerPedidoPorId(PedidoId pedidoId) {
		
		Long id = pedidoId.getValue();
		
		return pedidoPLRepository.findById(id).stream()
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
	public boolean existePedidoPorId(PedidoId pedidoId) {
		return pedidoPLRepository.existsById(pedidoId.getValue());
	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);		
		pedidoPLRepository.save(pedidoPL);	
	}

}
