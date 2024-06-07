package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelDoacao;
import com.backend.com.backend.model.services.ModelDoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Doacao")
public class DoacaoController {

    @Autowired
    private ModelDoacaoService modelDoacaoService;

    @PostMapping
    public ModelDoacao createDoacao(@RequestBody ModelDoacao doacao) {
        return modelDoacaoService.save(doacao);
    }

    @GetMapping("/all")
    public List<ModelDoacao> getAllDoacoes() {
        return modelDoacaoService.getAllDoacoes();
    }

    @DeleteMapping("/{id}")
    public void deleteDoacao(@PathVariable Long id) {
        modelDoacaoService.deleteDoacao(id);
    }
}
