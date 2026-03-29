package com.br.farney.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.ParceiroTecnologia;
import com.br.farney.crudapi.model.TipoDispositivoEletronico;

@Repository
public interface ParceiroTecnologiaRepository extends JpaRepository<ParceiroTecnologia, Long> {

    List<ParceiroTecnologia> findByCidadeIgnoreCase(String cidade);

    List<ParceiroTecnologia> findByTiposDispositivosContaining(TipoDispositivoEletronico tipo);
}
