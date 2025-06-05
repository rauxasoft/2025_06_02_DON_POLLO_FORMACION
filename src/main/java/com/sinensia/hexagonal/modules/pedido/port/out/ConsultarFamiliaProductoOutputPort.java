package com.sinensia.hexagonal.modules.pedido.port.out;

import com.sinensia.hexagonal.modules.familia.domain.model.Familia;
import com.sinensia.hexagonal.modules.producto.domain.model.ProductoId;

public interface ConsultarFamiliaProductoOutputPort {

	// Importante la ubicación de este output port!
	// Lo tenemos en el modulo de pedido porque es donde se necesita
	
	Familia obtenerFamilia(ProductoId productoId);
}
