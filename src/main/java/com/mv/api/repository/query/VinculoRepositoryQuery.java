package com.mv.api.repository.query;

import java.util.List;

import com.mv.api.model.Estabelecimento;
import com.mv.api.model.Profissional;
import com.mv.api.model.Vinculo;

public interface VinculoRepositoryQuery {
	
	public boolean existeVinculoNesseEstabelecimentoParaEste(Profissional profissional, Estabelecimento estabelecimento);
	public List<Vinculo> buscarVinculosPara(Profissional profissional);
	public Vinculo buscaPorId(Long id);
}
