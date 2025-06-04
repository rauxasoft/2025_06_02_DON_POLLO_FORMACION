package com.sinensia.hexagonal.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sinensia.hexagonal.domain.model.Producto;

public interface ProductoOutputPort {

	Long crearProducto(Producto producto);

	Optional<Producto> obtenerProductoPorId(Long idProducto);

	boolean existeProductoPorId(Long idProducto);

	void actualizarProducto(Producto producto);

	void descatalogarProducto(Long idProducto);

	List<Producto> obtenerTodosLosProductos();

	int obtenerElNumeroTotalDeProductos();

}
