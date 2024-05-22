//package com.backend.com.backend.control;
//
//import com.backend.com.backend.model.entidades.ModelParametrizacao;
//import com.backend.com.backend.model.services.ModelParametrizacaoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/parametrizacao")
//public class ControlParametrizacao{
//
//    private final ModelParametrizacaoService parametrizacaoService;
//
//    @Autowired
//    public ControlParametrizacao(ModelParametrizacaoService parametrizacaoService) {
//        this.parametrizacaoService = parametrizacaoService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ModelParametrizacao> salvar(
//            @RequestParam("parametrizacaoNomeFantasia") String nomeFantasia,
//            @RequestParam("parametrizacaoRazaoSocial") String razaoSocial,
//            @RequestParam("parametrizacaoTelefone") String telefone,
//            @RequestParam("parametrizacaoemail") String email,
//            @RequestParam("parametrizacaoSite") String site,
//            @RequestParam("parametrizacaoLogradouro") String logradouro,
//            @RequestParam("parametrizacaoBairro") String bairro,
//            @RequestParam("parametrizacaoCEP") String cep,
//            @RequestParam("parametrizacaoNumero") int numero,
//            @RequestParam("parametrizacaoLogoTipoGrande") MultipartFile logoTipoGrande,
//            @RequestParam("parametrizacaoLogoTipoPequena") MultipartFile logoTipoPequena
//    ) throws IOException {
//        ModelParametrizacao parametrizacao = new ModelParametrizacao();
//        parametrizacao.setParametrizacaoNomeFantasia(nomeFantasia);
//        parametrizacao.setParametrizacaoRazaoSocial(razaoSocial);
//        parametrizacao.setParametrizacaoTelefone(telefone);
//        parametrizacao.setParametrizacaoemail(email);
//        parametrizacao.setParametrizacaoSite(site);
//        parametrizacao.setParametrizacaoLogradouro(logradouro);
//        parametrizacao.setParametrizacaoBairro(bairro);
//        parametrizacao.setParametrizacaoCEP(cep);
//        parametrizacao.setParametrizacaoNumero(numero);
//        parametrizacao.setParametrizacaoLogoTipoGrande(logoTipoGrande.getBytes());
//        parametrizacao.setParametrizacaoLogoTipoPequena(logoTipoPequena.getBytes());
//
//        ModelParametrizacao parametrizacaoSalva = parametrizacaoService.salvar(parametrizacao);
//        return ResponseEntity.status(HttpStatus.CREATED).body(parametrizacaoSalva);
//    }
//}
