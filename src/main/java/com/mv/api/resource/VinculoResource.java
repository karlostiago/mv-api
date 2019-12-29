package com.mv.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.mv.api.model.Vinculo;
import com.mv.api.service.VinculoService;

@RestController
@RequestMapping("vinculos")
public class VinculoResource {
	
	@Autowired
	private	VinculoService vinculoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/listar")
	public List<Vinculo> listar() {
		return vinculoService.todos();
	}
	
	@PostMapping("/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Vinculo> novo(@RequestBody Profissional profissional, HttpServletResponse response) {
		vinculoService.salvar(profissional);
		publisher.publishEvent(new CreateResourceEvent(this, response, profissional.getId()));
		return vinculoService.buscarPor(profissional);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		vinculoService.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Vinculo vinculo) {
	
		Vinculo vinculoSalvo = vinculoService.atualizar(id, vinculo);
		return ResponseEntity.ok(vinculoSalvo);
	}
}
