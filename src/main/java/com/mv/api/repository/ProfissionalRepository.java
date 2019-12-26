package com.mv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mv.api.model.Profissional;
import com.mv.api.repository.query.*;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, ProfissionalRepositoryQuery {

}
