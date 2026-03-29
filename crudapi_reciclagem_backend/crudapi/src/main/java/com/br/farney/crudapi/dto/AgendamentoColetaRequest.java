package com.br.farney.crudapi.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.br.farney.crudapi.model.MaterialTipo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgendamentoColetaRequest {

    @NotNull
    private Long usuarioId;

    private Long pontoColetaId;

    private Long parceiroLogisticaId;

    private Set<MaterialTipo> materiais;

    private String observacoes;

    private LocalDateTime dataAgendada;
}
