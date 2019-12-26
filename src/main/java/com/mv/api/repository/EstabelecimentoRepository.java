package com.mv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mv.api.model.Estabelecimento;
import com.mv.api.repository.query.EstabelecimentoRepositoryQuery;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long>, EstabelecimentoRepositoryQuery {

}
