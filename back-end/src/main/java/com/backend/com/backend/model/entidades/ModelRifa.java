package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelRifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="premio")
    private String premio;

    @Column(name="valor")
    private float valor;

    @Column(name="data_sorteio")
    private LocalDate dataSorteio;

    @Column(name="quantidade_tickets")
    private int quantidadeTickets;
}
