package com.sinensia.hexagonal.modules.pedido.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;

public interface PedidoOutputPort {

	Optional<Pedido> obtenerPedidoPorId(Long idPedido);

	List<Pedido> obtenerTodosLosPedidos();

	int obtenerElNumeroTotalDePedidos();

	boolean existePedidoPorId(Long idPedido);

	void actualizarPedido(Pedido pedido);

}
