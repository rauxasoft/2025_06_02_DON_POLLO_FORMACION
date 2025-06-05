package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller.config.ErrorResponse;
import com.sinensia.hexagonal.modules.producto.domain.model.Producto;
import com.sinensia.hexagonal.modules.producto.port.in.ProductoInputPort;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

	private ProductoInputPort productoInputPort;
	
	public ProductoController(ProductoInputPort productoInputPort) {
		this.productoInputPort = productoInputPort;
	}
	
	@GetMapping
	public ResponseEntity<?> getProductos(@RequestParam(required = false, defaultValue = "ALL") String view,
										  @RequestParam(required = false) Double min, 
										  @RequestParam(required = false) Double max) {
		
		Object respuesta = null;
		
		view = view.toUpperCase();
		
		switch(view) {
			
			case "ALL":  {
				
				if(min == null || max == null) {
					respuesta = productoInputPort.obtenerTodosLosProductos();
				} else {
					respuesta = productoInputPort.obtenerTodosLosProductosEntreUnRangoDePrecios(min, max);
				}
				
				break;
			}
				
			default:     respuesta = productoInputPort.obtenerTodosLosProductos(); break;
		}
		
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		Optional<Producto> optional = productoInputPort.obtenerProductoPorId(id);
		
		if(optional.isEmpty()) {
			return new ResponseEntity<>(new ErrorResponse("No existe el producto " + id), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Producto producto, UriComponentsBuilder ucb){
		
		Long id = productoInputPort.crearProducto(producto);
		URI uri = ucb.path("/productos/{id}").build(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Producto producto, @PathVariable Long id){
		
		// TODO
		//producto.setId(id);
		productoInputPort.actualizarProducto(producto);
	 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productoInputPort.descatalogarProducto(id);
	}
	
	// TODO Test controlador

	@GetMapping("/estadisticas/by-familia")
	public Map<String, Integer> getEstadisticaNumeroProductosByFamilia(){
		
		Map<String, Integer> resultados = new HashMap<>();
		
		productoInputPort.getEstadisticaNumeroProductosByFamilia().forEach((k, v) -> {
			resultados.put(k.getNombre(), v);
		});
		
		return resultados;
	}

	// TODO Test controlador
	
	@GetMapping("/estadisticas/precio-medio-by-familia")
	public Map<String, Double> getEstadisticaPrecioMedioProductosByFamilia(){
		
		Map<String, Double> resultados = new HashMap<>();
		
		productoInputPort.getEstadisticaPrecioMedioProductosByFamilia().forEach((k, v) -> {
			resultados.put(k.getNombre(), v);
		});
		
		return resultados;
	}
	

}
