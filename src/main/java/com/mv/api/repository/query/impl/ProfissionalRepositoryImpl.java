package com.mv.api.repository.query.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mv.api.model.Profissional;
import com.mv.api.repository.filter.ProfissionalFilter;
import com.mv.api.repository.query.ProfissionalRepositoryQuery;

public class ProfissionalRepositoryImpl implements ProfissionalRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Profissional> pesquisar(ProfissionalFilter profissionalFilter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT prof FROM Profissional prof ");
		sql.append("WHERE 1 = 1 ");
		sql.append(profissionalFilter.getNome() != null && !profissionalFilter.getNome().isEmpty() ? "AND prof.nome LIKE :nome" : "");
		TypedQuery<Profissional> query = manager.createQuery(sql.toString(), Profissional.class);
		
		if(profissionalFilter.getNome() != null &&  !profissionalFilter.getNome().isEmpty()) {			
			query.setParameter("nome", "%" + profissionalFilter.getNome() + "%");
		}
		
		return query.getResultList();
	}

}
