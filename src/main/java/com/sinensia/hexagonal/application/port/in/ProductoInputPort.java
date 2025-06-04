package com.sinensia.hexagonal.application.port.in;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sinensia.hexagonal.domain.model.Familia;
import com.sinensia.hexagonal.domain.model.Producto;

public interface ProductoInputPort {

	/**
	 * Si la id no es null lanza BusinessException
	 * 
	 */
	Long crearProducto(Producto producto);										
	
	Optional<Producto> obtenerProductoPorId(Long idProducto);							
	
	/**
	 * Si la id es null o no existe lanza BusinessException
	 * 
	 */
	void actualizarProducto(Producto producto);										
	
	/**
	 * Si la id es null o no existe lanza BusinessException
	 * 
	 */
	void descatalogarProducto(Long idProducto);										
	
	List<Producto> obtenerTodosLosProductos();
	
	List<Producto> obtenerTodosLosProductosDeUnaFamilia(Familia familia);
	List<Producto> obtenerTodosLosProductosEntreUnRangoDePrecios(double min, double max);
	List<Producto> obtenerTodosLosProductosDescatalogados();
	List<Producto> obtenerTodosLosProductosDadosDeAltaEnUnRangoDeFechas(Date desde, Date hasta);
	
	int obtenerNumeroTotalDeProductos();
	
	int obtenerElNumeroDeProductosDeUnaFamilia(Familia familia);
	
	/**
	 * Incrementa el precio de los productos por Familia
	 *  
	 * Ejemplo:
	 * 
	 * Si porcentaje = 2.0 => el producto se incrementa un 2%
	 * 
	 * Si el porcentaje es <= 0 se lanza BusinessException
	 * 
	 */
	void incrementarPrecios(Familia familia, double porcentaje);
	
	/**
	 * Incrementa el precio de la lista de productos que se aporta
	 *  
	 * Ejemplo:
	 * 
	 * Si porcentaje = 2.0 => el producto se incrementa un 2%
	 * 
	 * Si el porcentaje es <= 0 se lanza BusinessException
	 * 
	 */
	void incrementarPrecios(List<Producto> productos, double porcentaje);
	
	/**
	 * Incrementa el precio de los prouctos referenciados por id
	 *  
	 * Ejemplo:
	 * 
	 * Si porcentaje = 2.0 => el producto se incrementa un 2%
	 * 
	 * Si el porcentaje es <= 0 se lanza BusinessException
	 * 
	 */
	void incrementarPrecios(Long[] idsProducto, double porcentaje);

	/**
	 * Devuelve a través de un mapa la cantidad de productos por Familia
	 * 
	 */
	Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia();
	
	/**
	 * Devuelve a través de un mapa el precio medio de los productos por Familia
	 * 
	 */
	Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia();

}
