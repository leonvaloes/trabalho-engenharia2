package com.backend.com.backend.model.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Table(name="Produtos",uniqueConstraints ={@UniqueConstraint(columnNames = "Prod_nome")})
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="Prod_nome")
    private String Nome;
    @Column(name="Prod_estoque")
    private int estoque;
    @ManyToOne
    @JoinColumn(name="tp_id",nullable = false)
    private TipoProdutos tipoProdutos;

}
