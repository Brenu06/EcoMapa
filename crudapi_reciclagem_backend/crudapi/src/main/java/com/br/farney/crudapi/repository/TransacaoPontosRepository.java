package com.br.farney.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.TransacaoPontos;
import com.br.farney.crudapi.model.Usuario;

@Repository
public interface TransacaoPontosRepository extends JpaRepository<TransacaoPontos, Long> {

    List<TransacaoPontos> findByUsuarioOrderByDataHoraDesc(Usuario usuario);
}
