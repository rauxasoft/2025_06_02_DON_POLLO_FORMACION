package com.sinensia.hexagonal.modules.pedido.port.in;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;

public interface PedidoInputPort {

	/**
	 * Si la id no es null lanza BusinessException
	 * 
	 */
	Long crearPedido(Pedido pedido);										
	
	Optional<Pedido> obtenerPedidoPorId(Long idPedido);							
	
	/**
	 * Si la id es null o no existe lanza BusinessException
	 * 
	 * Si se intenta transicionar entre estados no validos se lanza IllegalStateException
	 * 
	 */
	void actualizarPedido(Pedido pedido);										
	
	List<Pedido> obtenerTodosLosPedidos();
	
	List<Pedido> obtenerTodosLosPedidosPorIdDeEstablcimiento(Long idEstablecimiento);
	List<Pedido> obtenerTodosLosPedidosEntreRangoDeFechas(Date desde, Date hasta);
	
	int obtenerElNumeroTotalDePedidos();
	
	/**
	 * Devuelve estadística del número de pedidos por establecimiento
	 * 
	 * La estadística devuelve el nombre (String) del establecimiento
	 *
	 */
	Map<String, Integer> obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimiento();
	
	/**
	 * Devuelve estadística del número de pedidos por establecimiento en un periodo
	 * 
	 * La estadística devuelve el nombre (String) del establecimiento
	 *
	 */
	Map<String, Integer> obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimientoEnUnPeriodo(Date desde, Date hasta);
	
	/**
	 * Devuelve estadística del importe medio de los pedidos en un periodo
	 * 
	 * La estadística devuelve el nombre (String) del establecimiento
	 *
	 */
	Map<String, Double> obtenerEstadisticaDelImpoteMedioDeLosPedidosParaCadaEstablecimientoEnUnPeriodo(Date desde, Date hasta);
	
	/**
	 * Devuelve estadística del importe medio de los pedidos
	 * 
	 * La estadística devuelve el nombre (String) del establecimiento
	 *
	 */
	Map<String, Double> obtenerEstadistivaDelImporteMedioDeLosPedidosParaCadaEstablecimiento();
	
	/**
	 * Devuelve estadística del número de pedidos realizados por cada dependiente
	 * 
	 * La estadística devuelve el nombre completo (String) del dependiente
	 *
	 */
	Map<String, Integer> obtenerEstadisticaDelNumerodePedidosRealizadosPorCadaDependiente();
	
	/**
	 * Devuelve estadística del número de pedidos realizados por cada dependiente en un periodo
	 * 
	 * La estadística devuelve el nombre completo (String) del dependiente
	 *
	 */
	Map<String, Integer> obtenerEstadisticaDelNumeroDePedidosRealizadosPorCadaDependienteEnUnPeriodo(Date desde, Date hasta);
	
}

