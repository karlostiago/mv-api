package com.mv.api.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mv.api.model.Profissional;
import com.mv.api.model.Telefone;
import com.mv.api.repository.ProfissionalRepository;
import com.mv.api.repository.filter.ProfissionalFilter;

@Service
public class ProfissionalService implements Serializable {

	private static final long serialVersionUID = 4879780111839276804L;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	public Profissional porId(Long id) {
		Optional<Profissional> profissional = profissionalRepository.findById(id);
		
		if(!profissional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return profissional.get();
	}
	
	@Transactional
	public Profissional atualizar(Long id, Profissional profissional) {
		Profissional profissionalSalvo = porId(id);
		telefoneService.remove(profissionalSalvo.getTelefones());
		BeanUtils.copyProperties(profissional, profissionalSalvo, "id");
		profissionalSalvo.setTelefones(profissional.getTelefones());
		
		if(!profissionalSalvo.getTelefones().isEmpty()) {
			for(Telefone telefone : profissionalSalvo.getTelefones()) {
				telefone.setProfissional(profissionalSalvo);
			}
		}
		
		return profissionalRepository.save(profissionalSalvo);
	}
	
	public void delete(Long id) {
		profissionalRepository.deleteById(id);
	}
	
	public Profissional salvar(Profissional profissional) {
		
		if(profissional.getTelefones() != null && !profissional.getTelefones().isEmpty()) {
			for(Telefone telefone : profissional.getTelefones()) {
				telefone.setProfissional(profissional);
			}
		}
		
		return profissionalRepository.save(profissional);
	}
	
	public List<Profissional> filtrar(ProfissionalFilter profissionalFilter) {
		return profissionalRepository.pesquisar(profissionalFilter);
	}
}
