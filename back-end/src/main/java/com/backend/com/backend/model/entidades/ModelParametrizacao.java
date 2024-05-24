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

    @Column(name = "parametrizacaoLogoTipoGrande", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] parametrizacaoLogoTipoGrande;

    @Column(name = "parametrizacaoLogoTipoPequena", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] parametrizacaoLogoTipoPequena;
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


    public long getParametrizacaoId() {
        return parametrizacaoId;
    }

    public String getParametrizacaoRazaoSocial() {
        return parametrizacaoRazaoSocial;
    }

    public String getParametrizacaoNomeFantasia() {
        return parametrizacaoNomeFantasia;
    }

    public byte[] getParametrizacaoLogoTipoGrande() {
        return parametrizacaoLogoTipoGrande;
    }

    public byte[] getParametrizacaoLogoTipoPequena() {
        return parametrizacaoLogoTipoPequena;
    }

    public String getParametrizacaoSite() {
        return parametrizacaoSite;
    }

    public String getParametrizacaoemail() {
        return parametrizacaoemail;
    }

    public String getParametrizacaoTelefone() {
        return parametrizacaoTelefone;
    }

    public String getParametrizacaoCEP() {
        return parametrizacaoCEP;
    }

    public String getParametrizacaoLogradouro() {
        return parametrizacaoLogradouro;
    }

    public String getParametrizacaoBairro() {
        return parametrizacaoBairro;
    }

    public int getParametrizacaoNumero() {
        return parametrizacaoNumero;
    }

    public void setParametrizacaoId(long parametrizacaoId) {
        this.parametrizacaoId = parametrizacaoId;
    }

    public void setParametrizacaoRazaoSocial(String parametrizacaoRazaoSocial) {
        this.parametrizacaoRazaoSocial = parametrizacaoRazaoSocial;
    }

    public void setParametrizacaoNomeFantasia(String parametrizacaoNomeFantasia) {
        this.parametrizacaoNomeFantasia = parametrizacaoNomeFantasia;
    }

    public void setParametrizacaoLogoTipoGrande(byte[] parametrizacaoLogoTipoGrande) {
        this.parametrizacaoLogoTipoGrande = parametrizacaoLogoTipoGrande;
    }

    public void setParametrizacaoLogoTipoPequena(byte[] parametrizacaoLogoTipoPequena) {
        this.parametrizacaoLogoTipoPequena = parametrizacaoLogoTipoPequena;
    }

    public void setParametrizacaoSite(String parametrizacaoSite) {
        this.parametrizacaoSite = parametrizacaoSite;
    }

    public void setParametrizacaoemail(String parametrizacaoemail) {
        this.parametrizacaoemail = parametrizacaoemail;
    }

    public void setParametrizacaoTelefone(String parametrizacaoTelefone) {
        this.parametrizacaoTelefone = parametrizacaoTelefone;
    }

    public void setParametrizacaoCEP(String parametrizacaoCEP) {
        this.parametrizacaoCEP = parametrizacaoCEP;
    }

    public void setParametrizacaoLogradouro(String parametrizacaoLogradouro) {
        this.parametrizacaoLogradouro = parametrizacaoLogradouro;
    }

    public void setParametrizacaoBairro(String parametrizacaoBairro) {
        this.parametrizacaoBairro = parametrizacaoBairro;
    }

    public void setParametrizacaoNumero(int parametrizacaoNumero) {
        this.parametrizacaoNumero = parametrizacaoNumero;
    }
}
