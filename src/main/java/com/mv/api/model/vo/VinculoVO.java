package com.mv.api.model.vo;

import java.io.Serializable;

public class VinculoVO implements Serializable {

	private static final long serialVersionUID = 8712250001084290621L;

	private Long id;
	private String nomeProfissional;
	private String nomeEstabelecimento;
	
	public VinculoVO() { }
	
	public VinculoVO(Long id, String nomeProfissional, String nomeEstabelecimento) {
		this.id = id;
		this.nomeProfissional = nomeProfissional;
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProfissional() {
		return nomeProfissional;
	}

	public void setNomeProfissional(String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
}
