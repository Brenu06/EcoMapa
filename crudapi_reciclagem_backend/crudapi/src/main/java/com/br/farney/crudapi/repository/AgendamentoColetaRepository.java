package com.br.farney.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.AgendamentoColeta;
import com.br.farney.crudapi.model.StatusAgendamento;
import com.br.farney.crudapi.model.Usuario;

@Repository
public interface AgendamentoColetaRepository extends JpaRepository<AgendamentoColeta, Long> {

    List<AgendamentoColeta> findByUsuario(Usuario usuario);

    List<AgendamentoColeta> findByStatus(StatusAgendamento status);
}
