package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelParametrizacao;
import com.backend.com.backend.model.services.ModelParametrizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ModelParametrizacao> salvar(@RequestBody ModelParametrizacao parametrizacao) {


        ModelParametrizacao parametrizacaoSalva = parametrizacaoService.salvar(parametrizacao);
        System.out.println("\n\n\nvoltou\n\n\n\n");
        return ResponseEntity.status(HttpStatus.CREATED).body(parametrizacaoSalva);
    }

    // Outros métodos do controlador para operações adicionais, como atualizar e excluir
}
