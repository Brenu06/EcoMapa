package com.br.farney.crudapi.dto;

import com.br.farney.crudapi.model.MaterialTipo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuscaInteligenteRequest {

    @NotNull
    private MaterialTipo material;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    /**
     * Raio de busca em km.
     */
    private Double raioKm = 10.0;
}
