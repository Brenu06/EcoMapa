package com.br.farney.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.MaterialTipo;
import com.br.farney.crudapi.model.PontoColeta;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {

    List<PontoColeta> findByCidadeIgnoreCase(String cidade);

    List<PontoColeta> findByMateriaisAceitosContaining(MaterialTipo material);
}
