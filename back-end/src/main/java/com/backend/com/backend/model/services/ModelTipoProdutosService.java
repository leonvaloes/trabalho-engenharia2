package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.repositorios.ModelTipoProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelTipoProdutosService {
    @Autowired
    private ModelTipoProdutosRepositorio tipoprorepo;

    public TipoProdutos salvar(TipoProdutos tipoProdutos) {
        return tipoprorepo.save(tipoProdutos);
    }

    public TipoProdutos getumTipoProduto(Long id) {
        return tipoprorepo.findById(id).get();
    }
    public List<TipoProdutos> findallTipoProduto(){
        return tipoprorepo.findAll();
    }
}
