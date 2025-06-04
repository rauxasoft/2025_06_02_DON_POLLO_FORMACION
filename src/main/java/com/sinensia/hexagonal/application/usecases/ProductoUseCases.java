package com.sinensia.hexagonal.application.usecases;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.hexagonal.application.port.in.ProductoInputPort;
import com.sinensia.hexagonal.application.port.out.ProductoOutputPort;
import com.sinensia.hexagonal.domain.exceptions.BusinessException;
import com.sinensia.hexagonal.domain.model.Familia;
import com.sinensia.hexagonal.domain.model.Producto;

import jakarta.transaction.Transactional;

@Service
public class ProductoUseCases implements ProductoInputPort {

	private final ProductoOutputPort productoOutputPort;
	
	public ProductoUseCases(ProductoOutputPort productoOutputPort) {
		this.productoOutputPort = productoOutputPort;
	}
	
	@Override
	public Long crearProducto(Producto producto) {
		return productoOutputPort.crearProducto(producto);
	}

	@Override
	public Optional<Producto> obtenerProductoPorId(Long idProducto) {
		return productoOutputPort.obtenerProductoPorId(idProducto);
	}

	@Override
	@Transactional
	public void actualizarProducto(Producto producto) {
		
		Long idProducto = producto.getId();
		
		if(idProducto == null) {
			throw new BusinessException("La id de establecimiento no puede ser null");
		}
		
		boolean existe = productoOutputPort.existeProductoPorId(idProducto);
		
		if(!existe) {
			throw new BusinessException("No existe el producto con id [" + idProducto + "]");
		}
		
		productoOutputPort.actualizarProducto(producto);
	}

	@Override
	public void descatalogarProducto(Long idProducto) {
		productoOutputPort.descatalogarProducto(idProducto);
		
	}

	@Override
	public List<Producto> obtenerTodosLosProductos() {
		return productoOutputPort.obtenerTodosLosProductos();
	}

	@Override
	public List<Producto> obtenerTodosLosProductosDeUnaFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosLosProductosEntreUnRangoDePrecios(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosLosProductosDescatalogados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosLosProductosDadosDeAltaEnUnRangoDeFechas(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int obtenerNumeroTotalDeProductos() {
		return productoOutputPort.obtenerElNumeroTotalDeProductos();
	}

	@Override
	public int obtenerElNumeroDeProductosDeUnaFamilia(Familia familia) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementarPrecios(Familia familia, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementarPrecios(List<Producto> productos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incrementarPrecios(Long[] idsProducto, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Familia, Integer> getEstadisticaNumeroProductosByFamilia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Familia, Double> getEstadisticaPrecioMedioProductosByFamilia() {
		// TODO Auto-generated method stub
		return null;
	}

}
