package com.br.farney.crudapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registros_descarte")
public class RegistroDescarte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"senha", "saldoPontos"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ponto_coleta_id")
    @JsonIgnoreProperties({"responsavel"})
    private PontoColeta pontoColeta;

    @Enumerated(EnumType.STRING)
    private MaterialTipo material;

    /**
     * Quantidade em kg (fictícia).
     */
    private BigDecimal quantidadeKg;

    /**
     * Impacto ambiental estimado em kg de CO2 evitado.
     */
    private BigDecimal impactoCo2Kg;

    private LocalDateTime dataRegistro = LocalDateTime.now();

    /**
     * Pontos gerados por este descarte.
     */
    private Integer pontosGerados;
}
