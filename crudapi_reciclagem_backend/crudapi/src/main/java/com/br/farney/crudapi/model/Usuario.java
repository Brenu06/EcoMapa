package com.br.farney.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Em produção, guardar hash/salt. Aqui é apenas ilustrativo.
     */
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario = TipoUsuario.CIDADAO;

    /**
     * Saldo de pontos ambientais acumulados pelo usuário.
     */
    @Column(nullable = false)
    private Integer saldoPontos = 0;
}
