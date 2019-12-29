package com.mv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mv.api.model.Vinculo;
import com.mv.api.repository.query.VinculoRepositoryQuery;

public interface VinculoRepository extends JpaRepository<Vinculo, Long>, VinculoRepositoryQuery {

}
