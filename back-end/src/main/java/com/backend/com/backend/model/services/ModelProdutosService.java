package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ProdutosModel;
import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.repositorios.ModelProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelProdutosService {
    @Autowired
    private ModelProdutosRepositorio prodrepo;
    @Autowired
    private ModelTipoProdutosService  tipoProdutosService;

    public ProdutosModel salvarProduto(ProdutosModel produto, Long tipoId) {
        TipoProdutos tipoProdutos = tipoProdutosService.getumTipoProduto(tipoId);
        produto.setTipoProdutos(tipoProdutos);
        return prodrepo.save(produto);
    }
    public ProdutosModel salvar(ProdutosModel produtos)
    {
        return prodrepo.save(produtos);
    }
    public List<ProdutosModel> getall()
    {
        return prodrepo.findAll();
    }
    public ProdutosModel getByid(Long id)
    {
        return prodrepo.findById(id).orElse(null);
    }
    public List<ProdutosModel> findallbytipoProd(Long id)
    {
        return prodrepo.findAllByTipoProdutosId(id);
    }
    public boolean deleteprod(Long id)
    {
        try {
            prodrepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<Map<String, Object>> findByNome(String nome) {
            List<ProdutosModel> produtos = prodrepo.findByNomeContains(nome);
            List<Map<String, Object>> produtosComTipo = new ArrayList<>();

            for (ProdutosModel produto : produtos) {
                Map<String, Object> produtoComTipo = new HashMap<>();
                produtoComTipo.put("id", produto.getId());
                produtoComTipo.put("nome", produto.getNome());
                produtoComTipo.put("estoque", produto.getEstoque());
                produtoComTipo.put("tipo", produto.getTipoProdutos().getNome());
                produtosComTipo.add(produtoComTipo);
            }

            return produtosComTipo;
    }

    public List<ProdutosModel> findByEstoque(int estoque) {
        return prodrepo.findByEstoque(estoque);
    }
}
