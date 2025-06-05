package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.hexagonal.modules.familia.domain.model.Familia;
import com.sinensia.hexagonal.modules.familia.port.in.FamiliaInputPort;

@RestController
@RequestMapping("/familias")
@CrossOrigin
public class FamiliaController {

	private FamiliaInputPort familiaInputPort;
	
	public FamiliaController(FamiliaInputPort familiaInputPort) {
		this.familiaInputPort = familiaInputPort;
	}
	
	@GetMapping
	public List<Familia> getAll() throws Exception {
		
		Thread.sleep(1200);
		
		return familiaInputPort.obtenerTodasLasFamilias();
	}
	
}
