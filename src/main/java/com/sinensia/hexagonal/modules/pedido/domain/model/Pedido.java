package com.sinensia.hexagonal.modules.pedido.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.sinensia.hexagonal.modules.cliente.domain.model.ClienteId;
import com.sinensia.hexagonal.modules.dependiente.domain.model.DependienteId;
import com.sinensia.hexagonal.modules.establecimiento.domain.model.EstablecimientoId;
import com.sinensia.hexagonal.shared.domain.exception.BusinessException;

public class Pedido {
	private final PedidoId id;
	private final ClienteId clienteId;
	private final DependienteId dependienteId;
	private final EstablecimientoId establecimientoId;
	private EstadoPedido estado;
	private final List<LineaDetalle> lineas;
	
	public Pedido(PedidoId id, ClienteId clienteId, DependienteId dependienteId, EstablecimientoId establecimientoId) {
		
		if (id == null) {
			throw new BusinessException("El ID del pedido no puede ser nulo.");
		}
		
		if (clienteId == null) {
			throw new BusinessException("El cliente del pedido no puede ser nulo.");
		}
		
		if (dependienteId == null) {
			throw new BusinessException("El dependiente del pedido no puede ser nulo.");
		}
		
		if (establecimientoId == null) {
			throw new BusinessException("El establecimiento del pedido no puede ser nulo.");
		}
		
		this.id = id;
		this.clienteId = clienteId;
		this.dependienteId = dependienteId;
		this.establecimientoId = establecimientoId;
		this.estado = EstadoPedido.NUEVO;
		this.lineas = new ArrayList<>();
	}

	public PedidoId getId() {
		return id;
	}

	public ClienteId getClienteId() {
		return clienteId;
	}

	public DependienteId getDependienteId() {
		return dependienteId;
	}

	public EstablecimientoId getEstablecimientoId() {
		return establecimientoId;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public List<LineaDetalle> getLineas() {
		return Collections.unmodifiableList(lineas);
	}

	public void agregarLinea(LineaDetalle linea) {
		if (estado != EstadoPedido.EN_PROCESO) {
			throw new BusinessException("No se pueden añadir líneas a un pedido que no está pendiente.");
	    }
		this.lineas.add(linea);
	}
	
	public void marcarComoEnviado() {
		if (estado != EstadoPedido.PENDIENTE_ENTREGA) {
			throw new BusinessException("Solo se puede enviar un pedido pendiente.");
		}
		this.estado = EstadoPedido.CANCELADO;
	}

	public void cancelar() {
		if (estado == EstadoPedido.CANCELADO) {
			throw new BusinessException("No se puede cancelar un pedido ya enviado.");
		}
		this.estado = EstadoPedido.CANCELADO;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pedido)) return false;
		Pedido pedido = (Pedido) o;
		return id.equals(pedido.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}


/*


✅ Lo que estás diciendo es totalmente cierto:
Cuando el frontend envía un POST /pedidos, lo hace con toda la información montada: cliente, establecimiento, dependiente, líneas de pedido, etc. Así que sí, la creación del agregado Pedido debe incluir sus líneas.

Y de hecho, eso es lo que está haciendo el constructor actual:

this.lineasDetalle.addAll(lineasDetalle);
Pero para que esto funcione, necesitas que en el constructor se reciba una List<LineaDetalle> válida desde el exterior (por ejemplo, desde el PedidoInputPort), que a su vez ha sido construida previamente (normalmente en un DTO → Mapper → Modelo de dominio).

🔍 ¿Entonces por qué parece que se inicializa vacío?
Es solo una estrategia de encapsulación:

private final List<LineaDetalle> lineasDetalle = new ArrayList<>();
Esto crea una lista interna protegida contra modificación externa, y luego se llena:


this.lineasDetalle.addAll(lineasDetalle);
No se está creando con una lista vacía, sino inicializando primero el campo (por defecto) y luego llenándolo. Esta técnica:

Garantiza que la lista nunca sea null.

Permite usar Collections.unmodifiableList(...) en el getter.

Evita fugas de referencia a la colección original.

📌 En resumen:
Sí, el frontend envía el pedido completo y así debe funcionar. Lo importante es que el Pedido se construya con toda su información ya validada y mapeada, incluyendo las líneas. Eso ya está previsto en el diseño actual. Lo que quizás confunde es la inicialización defensiva de la lista, pero no implica que esté vacía tras construir el objeto.

*/
