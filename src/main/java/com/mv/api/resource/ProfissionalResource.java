package com.mv.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mv.api.event.CreateResourceEvent;
import com.mv.api.model.Profissional;
import com.mv.api.repository.filter.ProfissionalFilter;
import com.mv.api.service.ProfissionalService;

@RestController
@RequestMapping("profissionais")
public class ProfissionalResource {
	
	@Autowired
	private	ProfissionalService profissionalService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/listar")
	public List<Profissional> listar(ProfissionalFilter profissionalFilter) {
		return profissionalService.filtrar(profissionalFilter);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> novo(@Valid @RequestBody Profissional profissional, HttpServletResponse response) {
		Profissional profissionalSalvo = profissionalService.salvar(profissional);
		publisher.publishEvent(new CreateResourceEvent(this, response, profissionalSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Profissional profissional = profissionalService.porId(id);
		return profissional != null ? ResponseEntity.ok(profissional) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		profissionalService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long id, @Valid @RequestBody Profissional profissional) {
		
		Profissional profissionalSalvo = profissionalService.atualizar(id, profissional);
		return ResponseEntity.ok(profissionalSalvo);
	}
}
