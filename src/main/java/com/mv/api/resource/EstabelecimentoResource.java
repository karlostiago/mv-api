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
import com.mv.api.model.Estabelecimento;
import com.mv.api.repository.filter.EstabelecimentoFilter;
import com.mv.api.service.EstabelecimentoService;

@RestController
@RequestMapping("estabelecimentos")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/listar")
	public List<Estabelecimento> listar(EstabelecimentoFilter estabelecimentoFilter) {
		return estabelecimentoService.filtrar(estabelecimentoFilter);
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> novo(@Valid @RequestBody Estabelecimento estabelecimento, HttpServletResponse response) {
		Estabelecimento estabelecimentoSalvo = estabelecimentoService.salvar(estabelecimento);
		publisher.publishEvent(new CreateResourceEvent(this, response, estabelecimentoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Estabelecimento estabelecimento = estabelecimentoService.porId(id);
		return estabelecimento != null ? ResponseEntity.ok(estabelecimento) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		estabelecimentoService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estabelecimento> atualizar(@PathVariable Long id, @Valid @RequestBody Estabelecimento estabelecimento) {
		
		Estabelecimento estabelecimentoSalvo = estabelecimentoService.atualizar(id, estabelecimento);
		return ResponseEntity.ok(estabelecimentoSalvo);
	}
}
