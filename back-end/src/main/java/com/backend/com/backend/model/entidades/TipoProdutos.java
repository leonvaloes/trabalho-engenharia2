package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "TipoProdutos",uniqueConstraints ={@UniqueConstraint(columnNames = "Tp_nome")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoProdutos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Tp_nome")
    private String nome;
}
