package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelParametrizacao;
import com.backend.com.backend.model.services.ModelParametrizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/parametrizacao")
public class ControlParmetrizacao {

    private final ModelParametrizacaoService parametrizacaoService;

    @Autowired
    public ControlParmetrizacao(ModelParametrizacaoService parametrizacaoService) {
        this.parametrizacaoService = parametrizacaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelParametrizacao> buscarPorId(@PathVariable long id) {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorId(id);
        if (parametrizacao != null) {
            return ResponseEntity.ok(parametrizacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPorNomeCompleto/{nomeCompleto}")
    public ResponseEntity <ModelParametrizacao> buscarPorNomeCompleto(@PathVariable String nomeCompleto)
    {
        ModelParametrizacao parametrizacao = parametrizacaoService.buscarPorNomeCompleto(nomeCompleto);
        if (parametrizacao!=null) {
            return ResponseEntity.ok(parametrizacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ModelParametrizacao> salvar(@RequestBody ModelParametrizacao parametrizacao) {
        ModelParametrizacao parametrizacaoSalva = parametrizacaoService.salvar(parametrizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(parametrizacaoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelParametrizacao> alterar(@PathVariable long id, @RequestBody ModelParametrizacao parametrizacao) {
        ModelParametrizacao parametrizacaoExistente = parametrizacaoService.buscarPorId(id);
        if (parametrizacaoExistente != null) {
            parametrizacao.setParametrizacaoId(id);
            ModelParametrizacao parametrizacaoAtualizada = parametrizacaoService.alterar(parametrizacao);
            return ResponseEntity.ok(parametrizacaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getImagePath(@PathVariable Long id) {
        String imagePath = imageService.getImagePathById(id); // Implement this method in your service
        return ResponseEntity.ok(imagePath);
    }



    // Outros métodos do controlador para operações adicionais, como atualizar e excluir
}
