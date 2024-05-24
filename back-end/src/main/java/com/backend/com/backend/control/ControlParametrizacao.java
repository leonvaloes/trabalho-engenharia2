package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelParametrizacao;
import com.backend.com.backend.model.services.ModelParametrizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/parametrizacao")
public class ControlParametrizacao {

    private final ModelParametrizacaoService parametrizacaoService;

    @Autowired
    public ControlParametrizacao(ModelParametrizacaoService parametrizacaoService) {
        this.parametrizacaoService = parametrizacaoService;
    }

    @PostMapping
    public ResponseEntity<ModelParametrizacao> salvar(
            @RequestParam("parametrizacaoNomeFantasia") String nomeFantasia,
            @RequestParam("parametrizacaoRazaoSocial") String razaoSocial,
            @RequestParam("parametrizacaoTelefone") String telefone,
            @RequestParam("parametrizacaoemail") String email,
            @RequestParam("parametrizacaoSite") String site,
            @RequestParam("parametrizacaoLogradouro") String logradouro,
            @RequestParam("parametrizacaoBairro") String bairro,
            @RequestParam("parametrizacaoCEP") String cep,
            @RequestParam("parametrizacaoNumero") int numero,
            @RequestParam("parametrizacaoLogoTipoGrande") MultipartFile logoTipoGrande,
            @RequestParam("parametrizacaoLogoTipoPequena") MultipartFile logoTipoPequena
    ) throws IOException {
        ModelParametrizacao parametrizacao = new ModelParametrizacao();
        parametrizacao.setParametrizacaoNomeFantasia(nomeFantasia);
        parametrizacao.setParametrizacaoRazaoSocial(razaoSocial);
        parametrizacao.setParametrizacaoTelefone(telefone);
        parametrizacao.setParametrizacaoemail(email);
        parametrizacao.setParametrizacaoSite(site);
        parametrizacao.setParametrizacaoLogradouro(logradouro);
        parametrizacao.setParametrizacaoBairro(bairro);
        parametrizacao.setParametrizacaoCEP(cep);
        parametrizacao.setParametrizacaoNumero(numero);
        parametrizacao.setParametrizacaoLogoTipoGrande(logoTipoGrande.getBytes());
        parametrizacao.setParametrizacaoLogoTipoPequena(logoTipoPequena.getBytes());

        ModelParametrizacao parametrizacaoSalva = parametrizacaoService.salvar(parametrizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(parametrizacaoSalva);
    }




    @GetMapping("/logo-grande")
    public ResponseEntity<byte[]> obterLogoTipoGrande(@RequestParam("parametrizacaoId") long parametrizacaoId) {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorId(parametrizacaoId);
        if (parametrizacao != null && parametrizacao.getParametrizacaoLogoTipoGrande() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(parametrizacao.getParametrizacaoLogoTipoGrande());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Rota para obter a imagem do logo pequeno
    @GetMapping("/logo-pequeno")
    public ResponseEntity<byte[]> obterLogoTipoPequeno(@RequestParam("parametrizacaoId") long parametrizacaoId) {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorId(parametrizacaoId);
        if (parametrizacao != null && parametrizacao.getParametrizacaoLogoTipoPequena() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(parametrizacao.getParametrizacaoLogoTipoPequena());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping
    public ResponseEntity<ModelParametrizacao> buscarPorId(@RequestParam("id") long id) {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorId(id);
        if (parametrizacao != null) {
            return ResponseEntity.ok().body(parametrizacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<ModelParametrizacao> alterar(
            @PathVariable Long id,
            @RequestParam("parametrizacaoNomeFantasia") String nomeFantasia,
            @RequestParam("parametrizacaoRazaoSocial") String razaoSocial,
            @RequestParam("parametrizacaoTelefone") String telefone,
            @RequestParam("parametrizacaoemail") String email,
            @RequestParam("parametrizacaoSite") String site,
            @RequestParam("parametrizacaoLogradouro") String logradouro,
            @RequestParam("parametrizacaoBairro") String bairro,
            @RequestParam("parametrizacaoCEP") String cep,
            @RequestParam("parametrizacaoNumero") int numero,
            @RequestParam("parametrizacaoLogoTipoGrande") MultipartFile logoTipoGrande,
            @RequestParam("parametrizacaoLogoTipoPequena") MultipartFile logoTipoPequena
    ) throws IOException {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorId(id);
        if (parametrizacao != null) {
            parametrizacao.setParametrizacaoNomeFantasia(nomeFantasia);
            parametrizacao.setParametrizacaoRazaoSocial(razaoSocial);
            parametrizacao.setParametrizacaoTelefone(telefone);
            parametrizacao.setParametrizacaoemail(email);
            parametrizacao.setParametrizacaoSite(site);
            parametrizacao.setParametrizacaoLogradouro(logradouro);
            parametrizacao.setParametrizacaoBairro(bairro);
            parametrizacao.setParametrizacaoCEP(cep);
            parametrizacao.setParametrizacaoNumero(numero);
            parametrizacao.setParametrizacaoLogoTipoGrande(logoTipoGrande.getBytes());
            parametrizacao.setParametrizacaoLogoTipoPequena(logoTipoPequena.getBytes());

            ModelParametrizacao parametrizacaoAlterada = parametrizacaoService.alterar(parametrizacao);
            if (parametrizacaoAlterada != null) {
                return ResponseEntity.ok(parametrizacaoAlterada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
