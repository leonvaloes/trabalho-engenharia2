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
public class ModelParametrizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long parametrizacaoId;
    @Column(name = "parametrizacaoRazaoSocial")
    private String parametrizacaoRazaoSocial;
    @Column(name = "parametrizacaoNomeFantasia")

    private String parametrizacaoNomeFantasia;
    @Column(name = "parametrizacaoLogoTipoGrande")

    private String parametrizacaoLogoTipoGrande;
    @Column(name = "parametrizacaoLogoTipoPequena")
    private String parametrizacaoLogoTipoPequena;
    @Column(name = "parametrizacaoSite")
    private String parametrizacaoSite;
    @Column(name = "parametrizacaoemail")
    private String parametrizacaoemail;
    @Column(name = "parametrizacaoTelefone")
    private String parametrizacaoTelefone;
    @Column(name = "parametrizacaoCEP")
    private String parametrizacaoCEP;
    @Column(name = "parametrizacaoLogradouro")
    private String parametrizacaoLogradouro;
    @Column(name = "parametrizacaoBairro")
    private String parametrizacaoBairro;
    @Column(name = "parametrizacaoNumero")
    private int parametrizacaoNumero;

}
