package com.mv.api.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mv.api.model.Estabelecimento;
import com.mv.api.model.Profissional;
import com.mv.api.model.Vinculo;
import com.mv.api.model.vo.VinculoVO;
import com.mv.api.repository.VinculoRepository;

@Service
public class VinculoService implements Serializable {

	private static final long serialVersionUID = -8148209997521461024L;
	
	@Autowired
	private VinculoRepository vinculoRepository;
	
	public void salvar(Profissional profissional) {
		if(!profissional.getEstabelecimentos().isEmpty()) {
			for(Estabelecimento estabelecimento : profissional.getEstabelecimentos()) {
				if(!vinculoRepository.existeVinculoNesseEstabelecimentoParaEste(profissional, estabelecimento)) {					
					vinculoRepository.save(new Vinculo(profissional, estabelecimento));
				}
			}
		}
	}
	
	@Transient
	public Vinculo atualizar(Long id, Vinculo vinculo) {
		Vinculo vinculoSalvo = vinculoRepository.buscaPorId(id);
		
		if(vinculoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(vinculo, vinculoSalvo, "id");
		return vinculoRepository.save(vinculoSalvo);
	}
	
	public void remover(Long id) {
		vinculoRepository.deleteById(id);
	}
	
	public List<VinculoVO> todos() {
		return vinculoRepository.todos();
	}
	
	public List<Vinculo> buscarPor(Profissional profissional) {
		return vinculoRepository.buscarVinculosPara(profissional);
	}
}
