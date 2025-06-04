package com.sinensia.hexagonal.application.usecases;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.PedidoInputPort;
import com.sinensia.hexagonal.application.port.out.PedidoOutputPort;
import com.sinensia.hexagonal.domain.exceptions.BusinessException;
import com.sinensia.hexagonal.domain.model.Pedido;

@Service
public class PedidoUseCases implements PedidoInputPort {

	private final PedidoOutputPort pedidoOutputPort;
	
	public PedidoUseCases(PedidoOutputPort pedidoOutputPort) {
		this.pedidoOutputPort = pedidoOutputPort;
	}
	
	@Override
	public Long crearPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pedido> obtenerPedidoPorId(Long idPedido) {
		return pedidoOutputPort.obtenerPedidoPorId(idPedido);
	}

	@Override
	public void actualizarPedido(Pedido pedido) {
		
		Long idPedido = pedido.getId();
		
		if(idPedido == null) {
			throw new BusinessException("La id del pedido no puede ser null");
		}
		
		boolean existe = pedidoOutputPort.existePedidoPorId(idPedido);
		
		if(!existe) {
			throw new BusinessException("No existe el pedido con id [" + idPedido + "]");
		}
		
		pedidoOutputPort.actualizarPedido(pedido);
		
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidos() {
		return pedidoOutputPort.obtenerTodosLosPedidos();
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidosPorIdDeEstablcimiento(Long idEstablecimiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> obtenerTodosLosPedidosEntreRangoDeFechas(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int obtenerElNumeroTotalDePedidos() {
		return pedidoOutputPort.obtenerElNumeroTotalDePedidos();
	}

	@Override
	public Map<String, Integer> obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimiento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimientoEnUnPeriodo(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> obtenerEstadisticaDelImpoteMedioDeLosPedidosParaCadaEstablecimientoEnUnPeriodo(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> obtenerEstadistivaDelImporteMedioDeLosPedidosParaCadaEstablecimiento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> obtenerEstadisticaDelNumerodePedidosRealizadosPorCadaDependiente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> obtenerEstadisticaDelNumeroDePedidosRealizadosPorCadaDependienteEnUnPeriodo(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

}
