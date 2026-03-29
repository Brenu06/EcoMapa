package com.br.farney.crudapi.model;

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
@Table(name = "transacoes_pontos")
public class TransacaoPontos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"senha", "saldoPontos"})
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoTransacaoPontos tipo;

    private Integer pontos;

    private String descricao;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "registro_descarte_id")
    private RegistroDescarte registroDescarte;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensa;
}
