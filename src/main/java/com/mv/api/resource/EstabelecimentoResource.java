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

import com.mv.api.model.Estabelecimento;
import com.mv.api.repository.EstabelecimentoRepository;

@RestController
@RequestMapping("estabelecimentos")
public class EstabelecimentoResource {
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@GetMapping("/listar")
	public List<Estabelecimento> listar() {
		return estabelecimentoRepository.findAll();
	}
	
	@PostMapping("/novo")
	public ResponseEntity<?> novo(@RequestBody Estabelecimento estabelecimento, HttpServletResponse response) {
		Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(estabelecimentoSalvo.getId()).toUri();
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(estabelecimentoSalvo);
	}
	
	@GetMapping("/novo/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		return estabelecimento.isPresent() ? ResponseEntity.ok(estabelecimento) : ResponseEntity.notFound().build();
	}
}
