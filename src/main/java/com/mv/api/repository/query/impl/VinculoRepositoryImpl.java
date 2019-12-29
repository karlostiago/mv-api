package com.mv.api.repository.query.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mv.api.model.Estabelecimento;
import com.mv.api.model.Profissional;
import com.mv.api.model.Vinculo;
import com.mv.api.model.vo.VinculoVO;
import com.mv.api.repository.query.VinculoRepositoryQuery;

public class VinculoRepositoryImpl implements VinculoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean existeVinculoNesseEstabelecimentoParaEste(Profissional profissional, Estabelecimento estabelecimento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(vinculo) FROM Vinculo vinculo ");
		sql.append("WHERE vinculo.profissional = :profissional AND vinculo.estabelecimento = :estabelecimento ");
		TypedQuery<Long> query = manager.createQuery(sql.toString(), Long.class);
		query.setParameter("profissional", profissional);
		query.setParameter("estabelecimento", estabelecimento);		
		return query.getSingleResult() > 0;
	}

	@Override
	public List<Vinculo> buscarVinculosPara(Profissional profissional) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT vinculo FROM Vinculo vinculo WHERE 1 = 1 AND vinculo.profissional = :profissional");
		TypedQuery<Vinculo> query = manager.createQuery(sql.toString(), Vinculo.class);
		query.setParameter("profissional", profissional);
		return query.getResultList();
	}

	@Override
	public Vinculo buscaPorId(Long id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT vinculo FROM Vinculo vinculo ");
			sql.append("LEFT JOIN FETCH vinculo.profissional ");
			sql.append("LEFT JOIN FETCH vinculo.estabelecimento ");
			sql.append("WHERE vinculo.id = : id ");
			
			TypedQuery<Vinculo> query = manager.createQuery(sql.toString(), Vinculo.class);
			query.setParameter("id", id);
						
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<VinculoVO> todos() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NEW com.mv.api.model.vo.VinculoVO(vinculo.id, profissional.nome, estabelecimento.nome) FROM Vinculo vinculo ");
		sql.append("LEFT JOIN vinculo.profissional profissional ");
		sql.append("LEFT JOIN vinculo.estabelecimento estabelecimento ");
		sql.append("WHERE 1 = 1");
		TypedQuery<VinculoVO> query = manager.createQuery(sql.toString(), VinculoVO.class);
		return query.getResultList();
	}
}
