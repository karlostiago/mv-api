package com.mv.api.repository.query.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mv.api.model.Estabelecimento;
import com.mv.api.repository.filter.EstabelecimentoFilter;
import com.mv.api.repository.query.EstabelecimentoRepositoryQuery;

public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estabelecimento> pesquisar(EstabelecimentoFilter estabelecimentoFilter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT estab FROM Estabelecimento estab ");
		sql.append("WHERE 1 = 1 ");
		sql.append(estabelecimentoFilter.getNome() != null && !estabelecimentoFilter.getNome().isEmpty() ? "AND estab.nome LIKE :nome" : "");
		TypedQuery<Estabelecimento> query = manager.createQuery(sql.toString(), Estabelecimento.class);
		
		if(estabelecimentoFilter.getNome() != null &&  !estabelecimentoFilter.getNome().isEmpty()) {			
			query.setParameter("nome", "%" + estabelecimentoFilter.getNome() + "%");
		}
		
		return query.getResultList();
	}

}
