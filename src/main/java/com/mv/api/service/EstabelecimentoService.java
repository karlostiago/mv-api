package com.mv.api.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mv.api.model.Estabelecimento;
import com.mv.api.model.Telefone;
import com.mv.api.repository.EstabelecimentoRepository;
import com.mv.api.repository.filter.EstabelecimentoFilter;

@Service
public class EstabelecimentoService implements Serializable {

	private static final long serialVersionUID = 4879780111839276804L;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	public Estabelecimento porId(Long id) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		
		if(!estabelecimento.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return estabelecimento.get();
	}
	
	@Transactional
	public Estabelecimento atualizar(Long id, Estabelecimento estabelecimento) {
		Estabelecimento estabelecimentoSalvo = porId(id);
		telefoneService.remove(estabelecimento.getTelefones());
		BeanUtils.copyProperties(estabelecimento, estabelecimentoSalvo, "id");
		estabelecimentoSalvo.setTelefones(estabelecimento.getTelefones());
		
		if(!estabelecimentoSalvo.getTelefones().isEmpty()) {
			for(Telefone telefone : estabelecimentoSalvo.getTelefones()) {
				telefone.setEstabelecimento(estabelecimentoSalvo);
			}
		}
		
		return estabelecimentoRepository.save(estabelecimentoSalvo);
	}
	
	public void delete(Long id) {
		estabelecimentoRepository.deleteById(id);
	}
	
	public Estabelecimento salvar(Estabelecimento estabelecimento) {
		
		if(estabelecimento.getTelefones() != null && !estabelecimento.getTelefones().isEmpty()) {
			for(Telefone telefone : estabelecimento.getTelefones()) {
				telefone.setEstabelecimento(estabelecimento);
			}
		}
		
		return estabelecimentoRepository.save(estabelecimento);
	}
	
	public List<Estabelecimento> filtrar(EstabelecimentoFilter estabelecimentoFilter) {
		return estabelecimentoRepository.pesquisar(estabelecimentoFilter);
	}
}
