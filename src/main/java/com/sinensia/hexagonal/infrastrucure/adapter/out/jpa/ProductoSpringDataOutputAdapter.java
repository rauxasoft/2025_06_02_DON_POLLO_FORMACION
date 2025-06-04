package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Repository;

import com.sinensia.hexagonal.application.port.out.ProductoOutputPort;
import com.sinensia.hexagonal.domain.model.Producto;
import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories.ProductoPLRepository;

@Repository
public class ProductoSpringDataOutputAdapter implements ProductoOutputPort {

	private final ProductoPLRepository productoPLRepository;
	private final DozerBeanMapper mapper;
	
	public ProductoSpringDataOutputAdapter(ProductoPLRepository productoPLRepository, DozerBeanMapper mapper) {
		this.productoPLRepository = productoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Long crearProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> obtenerProductoPorId(Long idProducto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existeProductoPorId(Long idProducto) {
		return productoPLRepository.existsById(idProducto);
	}

	@Override
	public void actualizarProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void descatalogarProducto(Long idProducto) {
		productoPLRepository.descatalogarProducto(idProducto);	
	}

	@Override
	public List<Producto> obtenerTodosLosProductos() {
		
		return productoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Producto.class))
				.toList();
	}

	@Override
	public int obtenerElNumeroTotalDeProductos() {
		return (int) productoPLRepository.count();
	}

}
