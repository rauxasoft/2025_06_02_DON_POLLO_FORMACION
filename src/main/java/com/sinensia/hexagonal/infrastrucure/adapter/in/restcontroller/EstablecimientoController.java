package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.hexagonal.application.port.in.EstablecimientoInputPort;
import com.sinensia.hexagonal.domain.model.Establecimiento;
import com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller.config.ErrorResponse;

@RestController
@RequestMapping("/establecimientos")
@CrossOrigin
public class EstablecimientoController {
	
	private final EstablecimientoInputPort establecimientoInputPort;
	
	public EstablecimientoController(EstablecimientoInputPort establecimientoServices) {
		this.establecimientoInputPort = establecimientoServices;
	}

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "ALL") String view){
		
		Object respuesta = null;
		
		view = view.toUpperCase();
		
		switch(view) {
			case "ALL":  respuesta = establecimientoInputPort.obtenerTodosLosEstablecimientos(); break;
			default:     respuesta = establecimientoInputPort.obtenerTodosLosEstablecimientos(); break;
		}
		
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		Optional<Establecimiento> optional = establecimientoInputPort.obtenerEstablecimientoPorId(id);
		
		if(optional.isEmpty()) {
			return new ResponseEntity<>(new ErrorResponse("No existe el establecimiento " + id), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(optional.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Establecimiento establecimiento, UriComponentsBuilder ucb){
		
		Long id = establecimientoInputPort.crearEstablecimiento(establecimiento);
		URI uri = ucb.path("/establecimientos/{id}").build(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Establecimiento establecimiento, @PathVariable Long id){
		
		establecimiento.setId(id);
		establecimientoInputPort.actualizarEstablecimiento(establecimiento);

	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Map<String, Object> values) {
		
		Optional<Establecimiento> optional = establecimientoInputPort.obtenerEstablecimientoPorId(id);
		
		if(optional.isEmpty()) {
			return new ResponseEntity<>(new ErrorResponse("No existe el establecimiento " + id), HttpStatus.NOT_FOUND);
		}
		
		Establecimiento establecimiento = optional.get();
		
		values.forEach((k, v) -> {
			
			if("nombre".equals(k)) {
				establecimiento.setNombre((String) v);
			}
			
			if("telefono1".equals(k)) {
				establecimiento.getDatosContacto().setTelefono1((String) v);
			}
			
			if("telefono2".equals(k)) {
				establecimiento.getDatosContacto().setTelefono2((String) v);
			}
		});
		
		establecimientoInputPort.actualizarEstablecimiento(establecimiento);
		
		return ResponseEntity.noContent().build(); 
	}

}
