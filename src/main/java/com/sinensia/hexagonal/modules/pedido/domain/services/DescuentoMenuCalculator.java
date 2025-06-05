package com.sinensia.hexagonal.modules.pedido.domain.services;

import com.sinensia.hexagonal.modules.pedido.domain.model.Pedido;
import com.sinensia.hexagonal.modules.pedido.port.out.ConsultarFamiliaProductoOutputPort;

public class DescuentoMenuCalculator {

	// Esto qué es?
	
	// Esto es un servicio de dominio. Una clase que... 
	
	// 1.- contiene lógica de negocio significativa
	// 2.- no encaja naturalmente en una sola entidad o value object
	// 3.- opera sobre uno o más agregados, sin pertenecer a ninguno
	// 4.- es pura! Sin dependencias técnicas
	
	// ConsultarFamiliaProductoOutputPort no es más que una interfaz que responde a preguntas de negocio. -> Se le puede pasar sin problemas!
	
	// Este servicio no es un caso de uso! Lo podríamos haber implementado directamente en un caso de uso. Pero..
	// ... de esta manera podemos testearlo de forma independiente. 
	// ... se podría reutilizar esta lógica en otros casos de uso.
	// ... si la lógica es "chunga", la entendemos mejor aislada.
	
	public double calcularDescuentoMenu(Pedido pedido, ConsultarFamiliaProductoOutputPort consultarFamilia) {
		
		// TODO
		
		// Aquí disponemos de toda la información para resolver el cálculo. 
		
		return 0.0;
	}
}
