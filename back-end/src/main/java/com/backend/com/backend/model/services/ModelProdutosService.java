package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ProdutosModel;
import com.backend.com.backend.model.repositorios.ModelProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelProdutosService {
    @Autowired
    private ModelProdutosRepositorio prodrepo;

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
        return prodrepo.findById(id).get();
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
    public ProdutosModel addEstoque(Long id, int qtde)
    {
        ProdutosModel produtosModel=prodrepo.findById(id).get();
        if(produtosModel!=null)
            produtosModel.setEstoque(produtosModel.getEstoque() +qtde);
        return produtosModel;
    }
    public ProdutosModel retiraEstoque(Long id, int qtde)
    {
        ProdutosModel produtosModel=prodrepo.findById(id).get();
        if (produtosModel!=null)
            produtosModel.setEstoque(produtosModel.getEstoque()-qtde);
        return produtosModel;
    }



}
