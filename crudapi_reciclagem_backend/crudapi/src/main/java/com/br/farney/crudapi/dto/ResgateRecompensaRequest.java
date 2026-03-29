package com.br.farney.crudapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResgateRecompensaRequest {

    @NotNull
    private Long usuarioId;
}
