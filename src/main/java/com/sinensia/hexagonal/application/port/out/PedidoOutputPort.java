package com.sinensia.hexagonal.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.domain.model.Pedido;

public interface PedidoOutputPort {

	Optional<Pedido> obtenerPedidoPorId(Long idPedido);

	List<Pedido> obtenerTodosLosPedidos();

	int obtenerElNumeroTotalDePedidos();

	boolean existePedidoPorId(Long idPedido);

	void actualizarPedido(Pedido pedido);

}
