package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ModelParametrizacao;
import com.backend.com.backend.model.repositorios.ModelParametrizacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelParametrizacaoService {

    private final ModelParametrizacaoRepositorio parametrizacaoRepository;

    @Autowired
    public ModelParametrizacaoService(ModelParametrizacaoRepositorio parametrizacaoRepository) {
        this.parametrizacaoRepository = parametrizacaoRepository;
    }

    public ModelParametrizacao buscarPorId(long id) {
        return parametrizacaoRepository.findById(id).orElse(null);
    }

    public ModelParametrizacao salvar(ModelParametrizacao parametrizacao) {
        return parametrizacaoRepository.save(parametrizacao);
    }

//    public ModelParametrizacao buscarPorNomeCompleto(String nomeCompleto) {
//        return parametrizacaoRepository.(nomeCompleto);
//    }

    public ModelParametrizacao alterar(ModelParametrizacao parametrizacao) {
        // Valida se o registro existe antes de alterar
        if (parametrizacaoRepository.existsById(parametrizacao.getParametrizacaoId())) {
            return parametrizacaoRepository.save(parametrizacao);
        } else {
            return null; // ou lançar uma exceção apropriada
        }
    }
}