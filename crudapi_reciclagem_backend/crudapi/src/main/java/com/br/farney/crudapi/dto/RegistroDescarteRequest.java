package com.br.farney.crudapi.dto;

import java.math.BigDecimal;

import com.br.farney.crudapi.model.MaterialTipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroDescarteRequest {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long pontoColetaId;

    @NotNull
    private MaterialTipo material;

    @NotNull
    @Min(0)
    private BigDecimal quantidadeKg;
}
