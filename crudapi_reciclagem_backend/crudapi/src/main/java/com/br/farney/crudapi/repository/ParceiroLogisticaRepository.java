package com.br.farney.crudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.ParceiroLogistica;

@Repository
public interface ParceiroLogisticaRepository extends JpaRepository<ParceiroLogistica, Long> {
}
