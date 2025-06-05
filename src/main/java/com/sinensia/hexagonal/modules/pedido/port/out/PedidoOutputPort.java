package com.sinensia.hexagonal.modules.pedido.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;
import com.sinensia.hexagonal.modules.pedido.domain.model.PedidoId;

public interface PedidoOutputPort {

	Optional<Pedido> obtenerPedidoPorId(PedidoId idPedido);

	List<Pedido> obtenerTodosLosPedidos();

	int obtenerElNumeroTotalDePedidos();

	boolean existePedidoPorId(PedidoId idPedido);

	void actualizarPedido(Pedido pedido);

}
