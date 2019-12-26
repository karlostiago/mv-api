package com.mv.api.repository.query;

import java.util.List;

import com.mv.api.model.Estabelecimento;
import com.mv.api.repository.filter.EstabelecimentoFilter;

public interface EstabelecimentoRepositoryQuery {
	
	public List<Estabelecimento> pesquisar(EstabelecimentoFilter estabelecimentoFilter);
}
