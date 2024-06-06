package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "TipoEventos", uniqueConstraints ={@UniqueConstraint(columnNames = "TpEvento_nome")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TpEvento_nome")
    private String nome;
}

