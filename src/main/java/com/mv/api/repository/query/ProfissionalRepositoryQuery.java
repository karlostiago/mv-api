package com.mv.api.repository.query;

import java.util.List;

import com.mv.api.model.Profissional;
import com.mv.api.repository.filter.ProfissionalFilter;

public interface ProfissionalRepositoryQuery {
	
	public List<Profissional> pesquisar(ProfissionalFilter profissionalFilter);
}
