package com.mv.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mv.api.model.Profissional;
import com.mv.api.repository.ProfissionalRepository;

@RestController
@RequestMapping("profissionais")
public class ProfissionalResource {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@GetMapping("/listar")
	public List<Profissional> listar() {
		return profissionalRepository.findAll();
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> novo(@RequestBody Profissional profissional, HttpServletResponse response) {
		Profissional profissionalSalvo = profissionalRepository.save(profissional);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(profissionalSalvo.getId()).toUri();
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(profissionalSalvo);
	}
	
	@GetMapping("/novo/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Profissional> profissional = profissionalRepository.findById(id);
		return profissional.isPresent() ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
}
