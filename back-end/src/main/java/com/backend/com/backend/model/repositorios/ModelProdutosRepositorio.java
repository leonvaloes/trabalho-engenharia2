package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelProdutosRepositorio extends JpaRepository<ProdutosModel,Long> {

    List<ProdutosModel> findAllByTipoProdutosId(Long id);
    List<ProdutosModel> findByNomeContains(String nome);

    List<ProdutosModel> findByEstoque(int estoque);

}
