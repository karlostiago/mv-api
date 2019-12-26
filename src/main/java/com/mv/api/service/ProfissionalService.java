package com.mv.api.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mv.api.model.Profissional;
import com.mv.api.repository.ProfissionalRepository;
import com.mv.api.repository.filter.ProfissionalFilter;

@Service
public class ProfissionalService implements Serializable {

	private static final long serialVersionUID = 4879780111839276804L;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	public Profissional porId(Long id) {
		Optional<Profissional> profissional = profissionalRepository.findById(id);
		
		if(!profissional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return profissional.get();
	}
	
	public Profissional atualizar(Long id, Profissional profissional) {
		Profissional profissionalSalvo = porId(id);
		BeanUtils.copyProperties(profissional, profissionalSalvo, "id");
		return profissionalRepository.save(profissionalSalvo);
	}
	
	public void delete(Long id) {
		profissionalRepository.deleteById(id);
	}
	
	public Profissional salvar(Profissional profissional) {
		return profissionalRepository.save(profissional);
	}
	
	public List<Profissional> filtrar(ProfissionalFilter profissionalFilter) {
		return profissionalRepository.pesquisar(profissionalFilter);
	}
}
