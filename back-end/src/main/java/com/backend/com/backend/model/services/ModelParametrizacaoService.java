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

    public ModelParametrizacao salvar(ModelParametrizacao parametrizacao) {
        System.out.println(parametrizacao);
        return parametrizacaoRepository.save(parametrizacao);

    }

    public ModelParametrizacao buscarPorId(long id) {
        return parametrizacaoRepository.findById(id).orElse(null);
    }

    // Outros métodos de serviço, como atualizar, excluir, listar, etc.
}