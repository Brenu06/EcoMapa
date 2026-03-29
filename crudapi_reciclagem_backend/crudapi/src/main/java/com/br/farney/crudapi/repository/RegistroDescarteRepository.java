package com.br.farney.crudapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.farney.crudapi.model.PontoColeta;
import com.br.farney.crudapi.model.RegistroDescarte;
import com.br.farney.crudapi.model.Usuario;

@Repository
public interface RegistroDescarteRepository extends JpaRepository<RegistroDescarte, Long> {

    List<RegistroDescarte> findByUsuario(Usuario usuario);

    List<RegistroDescarte> findByPontoColeta(PontoColeta pontoColeta);
}
