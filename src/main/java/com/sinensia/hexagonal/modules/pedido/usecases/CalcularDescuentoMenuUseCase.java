package com.sinensia.hexagonal.modules.pedido.usecases;

import java.util.Optional;

import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;
import com.sinensia.hexagonal.modules.pedido.domain.model.PedidoId;
import com.sinensia.hexagonal.modules.pedido.domain.services.DescuentoMenuCalculator;
import com.sinensia.hexagonal.modules.pedido.port.out.ConsultarFamiliaProductoOutputPort;
import com.sinensia.hexagonal.modules.pedido.port.out.PedidoOutputPort;
import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class CalcularDescuentoMenuUseCase {

	private final PedidoOutputPort pedidoOutputPort;
	private final ConsultarFamiliaProductoOutputPort consultarFamiliaProductoOutputPort;
	private final DescuentoMenuCalculator descuentoMenuCalculator;
	
	public CalcularDescuentoMenuUseCase(PedidoOutputPort pedidoOutputPort,
										ConsultarFamiliaProductoOutputPort consultarFamiliaProductoOutputPort,
										DescuentoMenuCalculator descuentoMenuCalculator) {
		
		this.pedidoOutputPort = pedidoOutputPort;
		this.consultarFamiliaProductoOutputPort = consultarFamiliaProductoOutputPort;
		this.descuentoMenuCalculator = descuentoMenuCalculator;
	}
	
	public double obtenerDescuentoMenu(PedidoId pedidoId) {
		Optional<Pedido> optional = pedidoOutputPort.obtenerPedidoPorId(pedidoId);
		Pedido pedido = optional.orElseThrow(() -> new BusinessException("No existe el pedido " + pedidoId));
		return descuentoMenuCalculator.calcularDescuentoMenu(pedido, consultarFamiliaProductoOutputPort);
	}
	
	
	
}
