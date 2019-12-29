package com.mv.api.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mv.api.model.Telefone;
import com.mv.api.repository.TelefoneRepository;

@Service
public class TelefoneService implements Serializable {

	private static final long serialVersionUID = 8232079806025610934L;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public void remove(List<Telefone> telefones) {
		if(!telefones.isEmpty()) {
			for(Telefone tel : telefones) {
				telefoneRepository.deleteById(tel.getId());
			}
		}
	}
}
