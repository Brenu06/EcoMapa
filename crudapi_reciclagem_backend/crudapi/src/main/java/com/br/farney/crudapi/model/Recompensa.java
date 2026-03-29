package com.br.farney.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recompensas")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private Integer pontosNecessarios;

    private boolean ativo = true;

    /**
     * Parceiro que oferece a recompensa (pode ser empresa/ONG).
     */
    private String parceiroNome;
}
