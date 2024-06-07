package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ModelDoacao;
import com.backend.com.backend.model.repositorios.ModelDoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelDoacaoService {

    @Autowired
    private ModelDoacaoRepository modelDoacaoRepository;

    public ModelDoacao save(ModelDoacao doacao) {
        return modelDoacaoRepository.save(doacao);
    }

    public List<ModelDoacao> getAllDoacoes() {
        return modelDoacaoRepository.findAll();
    }

    public void deleteDoacao(Long id) {
        modelDoacaoRepository.deleteById(id);
    }
}
