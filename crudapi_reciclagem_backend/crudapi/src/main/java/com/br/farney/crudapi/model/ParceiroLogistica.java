package com.br.farney.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parceiros_logistica")
public class ParceiroLogistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo; // cooperativa, empresa de logística reversa etc.

    private String telefone;
    private String email;
    private String cidade;
    private String estado;
}
