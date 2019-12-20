package com.mv.api.model;

import java.io.Serializable;

public class Estabelecimento implements Serializable {

	private static final long serialVersionUID = -4967676811777503255L;
	
	private String nome;
	private String endereco;
	private String telefone1;
	private String telefone2;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
}
