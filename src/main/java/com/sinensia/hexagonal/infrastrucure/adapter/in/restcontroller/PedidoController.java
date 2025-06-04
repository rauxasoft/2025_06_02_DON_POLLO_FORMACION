package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller;

import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.hexagonal.application.port.in.PedidoInputPort;
import com.sinensia.hexagonal.domain.model.Pedido;
import com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller.config.ErrorResponse;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {
	
	private PedidoInputPort pedidoInputPort;
	
	public PedidoController(PedidoInputPort pedidoInputPort) {
		this.pedidoInputPort = pedidoInputPort;
	}
	
	@GetMapping
	public ResponseEntity<?> getPedidos(@RequestParam(required = false, defaultValue = "ALL") String view){
		
		System.out.println("ESTO NO!");
		
		Object respuesta = null;
		
		view = view.toUpperCase();
		
		switch(view) {
			case "ALL":  respuesta = pedidoInputPort.obtenerTodosLosPedidos(); break;
			default:     respuesta = pedidoInputPort.obtenerTodosLosPedidos(); break;
		}
		
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPedidoById(@PathVariable Long id){
		
		Optional<Pedido> optional = pedidoInputPort.obtenerPedidoPorId(id);
		
		if(optional.isEmpty()) {
			return new ResponseEntity<>(new ErrorResponse("No existe el pedido " + id), HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido, UriComponentsBuilder ucb){
		
		Long id = pedidoInputPort.crearPedido(pedido);
		URI uri = ucb.path("/pedidos/{id}").build(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Pedido pedido) {
		
		pedido.setId(id);
		
		pedidoInputPort.actualizarPedido(pedido);
		
	}
	
	// TODO Test del endpoint
	
    @GetMapping("/estadisticas/por-establecimiento")
    public Map<String, Integer> getEstadistica1() {
        return pedidoInputPort.obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimiento();
    }
    
	@GetMapping("/estadisticas/by-establecimiento/fechas")
	public Map<String, Integer> getEstadisticaNumeroPedidosByEstablecimiento(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {

	        return pedidoInputPort.obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimientoEnUnPeriodo(desde, hasta);
	} 

}
