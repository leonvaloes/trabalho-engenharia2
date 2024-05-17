package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLongin;

    @Column(name ="userNome")
    private String userNome;

    @Column(name ="userSenha")
    private String userSenha;

    @ManyToOne
    @JoinColumn(name = "parametrizacaoId", referencedColumnName = "parametrizacaoId")
    private ModelParametrizacao parametrizacao;

}

