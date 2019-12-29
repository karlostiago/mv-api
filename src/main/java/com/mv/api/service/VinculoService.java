package com.mv.api.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mv.api.model.Vinculo;
import com.mv.api.repository.VinculoRepository;

@Service
public class VinculoService implements Serializable {

	private static final long serialVersionUID = -8148209997521461024L;
	
	@Autowired
	private VinculoRepository vinculoRepository;
	
	public void salvar(Vinculo vinculo) {
		this.vinculoRepository.save(vinculo);
	}
}
